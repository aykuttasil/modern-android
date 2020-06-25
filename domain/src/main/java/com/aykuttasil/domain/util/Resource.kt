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

  data class Error<out T>(val error: Throwable? = null) : Resource<T>(
    Status.ERROR
  )

  override fun toString(): String {
    return when (this) {
      is Success<*> -> "Success[data=$data]"
      is Error -> "Error[exception=${error?.message ?: "Error"}]"
      is Loading -> "Loading"
    }
  }
}

/**
 * `true` if [Resource] is of type [Resource.Success] & holds non-null [Resource.Success.data].
 */
val Resource<*>.succeeded
  get() = this is Resource.Success && data != null