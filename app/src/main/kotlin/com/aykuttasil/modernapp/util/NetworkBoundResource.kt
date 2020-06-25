/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aykuttasil.modernapp.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.aykuttasil.data.remote.ApiEmptyResponse
import com.aykuttasil.data.remote.ApiErrorResponse
import com.aykuttasil.data.remote.ApiResponse
import com.aykuttasil.data.remote.ApiSuccessResponse
import com.aykuttasil.domain.util.AppExecutors
import com.aykuttasil.domain.util.Resource

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor(
  private val appExecutors: AppExecutors
) {

  private val result = MediatorLiveData<Resource<ResultType>>()

  init {
    setValue(Resource.Loading())
    @Suppress("LeakingThis")
    val dbSource = loadFromDb()
    result.addSource(dbSource) { data ->
      result.removeSource(dbSource)
      if (shouldFetch(data)) {
        fetchFromNetwork(dbSource)
      } else {
        result.addSource(dbSource) { newData ->
          setValue(Resource.Success(newData))
        }
      }
    }
  }

  @MainThread
  private fun setValue(newValue: Resource<ResultType>) {
    appExecutors.mainThread.execute {
      if (result.value != newValue) {
        result.value = newValue
      }
    }
  }

  private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
    val apiResponse = createCall()

    // we re-attach dbSource as a new source, it will dispatch its latest value quickly
    result.addSource(dbSource) { _ ->
      setValue(Resource.Loading())
    }
    result.addSource(apiResponse) { response ->
      result.removeSource(apiResponse)
      result.removeSource(dbSource)
      when (response) {
        is ApiSuccessResponse -> {
          appExecutors.diskIO.execute {
            saveCallResult(processResponse(response))
            appExecutors.mainThread.execute {
              // we specially request a new live data,
              // otherwise we will get immediately last cached value,
              // which may not be updated with latest results received from network.
              result.addSource(loadFromDb()) { newData ->
                setValue(Resource.Success(newData))
              }
            }
          }
        }
        is ApiEmptyResponse -> {
          appExecutors.mainThread.execute {
            // reload from disk whatever we had
            result.addSource(loadFromDb()) { newData ->
              setValue(Resource.Success(newData))
            }
          }
        }
        is ApiErrorResponse -> {
          onFetchFailed()
          result.addSource(dbSource) {
            setValue(Resource.Error(Exception(response.errorMessage)))
          }
        }
      }
    }
  }

  fun asLiveData() = result as LiveData<Resource<ResultType>>

  protected open fun onFetchFailed() {}

  @WorkerThread
  protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

  @WorkerThread
  protected abstract fun saveCallResult(item: RequestType)

  @MainThread
  protected abstract fun shouldFetch(data: ResultType?): Boolean

  @MainThread
  protected abstract fun loadFromDb(): LiveData<ResultType>

  @MainThread
  protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
