package com.aykuttasil.domain.util

enum class Status {
  LOADING,
  SUCCESS,
  ERROR
}


sealed class Resource<out T>(val status: Status, val message: String? = null) {
  class Loading<out T> : Resource<T>(
    Status.LOADING
  )

  data class Success<out T>(val data: T?) : Resource<T>(
    Status.SUCCESS
  )

  data class Error<out T>(val throwable: Throwable? = null, val msg: String? = "Error") :
    Resource<T>(
      Status.ERROR, msg
    )
}