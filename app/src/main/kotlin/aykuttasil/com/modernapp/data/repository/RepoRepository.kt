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
package aykuttasil.com.modernapp.data.repository

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.remote.ApiService
import aykuttasil.com.modernapp.data.remote.model.Repo
import com.aykutasil.modernapp.Resource
import com.aykutasil.modernapp.util.AppExecutors
import com.aykutasil.network.ApiResponse
import com.aykutasil.network.NetworkBoundResource
import javax.inject.Inject

/**
 * Created by aykutasil on 1.03.2018.
 */
class RepoRepository @Inject constructor(val apiService: ApiService, val appExecutors: AppExecutors) {

  fun getUserRepos(user: String): LiveData<Resource<Repo>> {
    return object : NetworkBoundResource<Repo, Repo>(appExecutors) {
      override fun saveCallResult(item: Repo) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
      }

      override fun shouldFetch(data: Repo?): Boolean {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
      }

      override fun loadFromDb(): LiveData<Repo> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
      }

      override fun createCall(): LiveData<ApiResponse<Repo>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
      }
    }.asLiveData()
  }
}
