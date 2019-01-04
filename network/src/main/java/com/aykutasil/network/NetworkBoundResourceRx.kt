package com.aykutasil.network

import android.content.Context
import com.aykutasil.modernapp.util.isNetworkStatusAvailable
import io.reactivex.Flowable
import io.reactivex.Flowable.defer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.schedulers.Schedulers

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class NetworkBoundResourceRx<ResultType, RequestType>(context: Context) {

    private val result: Flowable<ResourceSealed<ResultType>>

    init {
        // Lazy disk observable.
        val diskObservable = defer {
            loadFromDb()
                    // Read from disk on Computation Scheduler
                    .subscribeOn(Schedulers.computation())
        }

        // Lazy network observable.
        val networkObservable = defer {
            createCall()
                    // Request API on IO Scheduler
                    .subscribeOn(Schedulers.io())
                    // Read/Write to disk on Computation Scheduler
                    .observeOn(Schedulers.computation())
                    .doOnNext { request: Response<RequestType> ->
                        if (request.isSuccessful) {
                            saveCallResult(processResponse(request))
                        }
                    }
                    .onErrorReturn { throwable: Throwable ->
                        when (throwable) {
                            is HttpException -> {
                                //throw Exceptions.propagate(NetworkExceptions.getNoServerConnectivityError(context))
                                throw Exceptions.propagate(Exception(""))
                            }
                            is IOException -> {
                                //throw Exceptions.propagate(NetworkExceptions.getNoNetworkConnectivityError(context))
                                throw Exceptions.propagate(Exception(""))
                            }
                            else -> {
                                //throw Exceptions.propagate(NetworkExceptions.getUnexpectedError(context))
                                throw Exceptions.propagate(Exception(""))
                            }
                        }
                    }
                    .flatMap { loadFromDb() }
        }

        result = when {
            context.isNetworkStatusAvailable() -> networkObservable
                    .map<ResourceSealed<ResultType>> { ResourceSealed.Success(it) }
                    .onErrorReturn { ResourceSealed.Failure(it) }
                    // Read results in Android Main Thread (UI)
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(ResourceSealed.Loading())
            else -> diskObservable
                    .map<ResourceSealed<ResultType>> { ResourceSealed.Success(it) }
                    .onErrorReturn { ResourceSealed.Failure(it) }
                    // Read results in Android Main Thread (UI)
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(ResourceSealed.Loading())
        }
    }

    fun asFlowable(): Flowable<ResourceSealed<ResultType>> {
        return result
    }

    private fun processResponse(response: Response<RequestType>): RequestType {
        return response.body()!!
    }

    protected abstract fun saveCallResult(request: RequestType)

    protected abstract fun loadFromDb(): Flowable<ResultType>

    protected abstract fun createCall(): Flowable<Response<RequestType>>
}