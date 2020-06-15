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


data class Resource1<out T>(val status: Status, val data: T?, val message: String?) {

  companion object {

    fun <T> success(data: T?): Resource1<T> {
      return Resource1(Status.SUCCESS, data, null)
    }

    fun <T> error(msg: String, data: T?): Resource1<T> {
      return Resource1(Status.ERROR, data, msg)
    }

    fun <T> loading(data: T?): Resource1<T> {
      return Resource1(Status.LOADING, data, null)
    }

  }
}

fun a() {
  val a = Resource1.success("")
  when (a.status) {
    Status.LOADING -> {

    }
    Status.SUCCESS -> {

    }
  }


  val b: Resource<String> = Resource.Success("selam")

  when (b) {
    is Resource.Success -> {
      b.data
    }
    is Resource.Loading -> {

    }
  }
}