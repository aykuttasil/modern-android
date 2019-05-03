package com.aykutasil.modernapp

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<T>(val status: Status, val data: T?, val message: String?) {

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (other == null || javaClass != other.javaClass) {
      return false
    }

    val resource = other as Resource<*>?

    if (status !== resource!!.status) {
      return false
    }
    if (if (message != null) message != resource!!.message else resource!!.message != null) {
      return false
    }
    return if (data != null) data == resource.data else resource.data == null
  }

  override fun hashCode(): Int {
    var result = status.hashCode()
    result = 11 * result + (message?.hashCode() ?: 0)
    result = 11 * result + (data?.hashCode() ?: 0)
    return result
  }

  override fun toString(): String {
    return "Resource{" +
        "status=" + status +
        ", message='" + message + '\'' +
        ", data=" + data +
        '}'
  }

  companion object {

    fun <T> success(data: T?): Resource<T> {
      return Resource(Status.SUCCESS, data, null)
    }

    fun <T> error(msg: String, data: T?): Resource<T> {
      return Resource(Status.ERROR, data, msg)
    }

    fun <T> loading(data: T?): Resource<T> {
      return Resource(Status.LOADING, data, null)
    }
  }
}

/*
class Resource<T> private constructor(val status: Status, val data: T?) {
    companion object {
        fun <T> error() = Resource(Status.ERROR, null)
        fun <T> success(data: T) = Resource(Status.SUCCESS, data)
        fun <T> loading(data: T) = Resource(Status.LOADING, data)
    }
}
*/

/*

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure<out T>(val throwable: Throwable) : Resource<T>()
}
 */