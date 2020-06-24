package com.aykuttasil.domain.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

  data class Success<out T : Any>(val data: T) : Result<T>()
  data class Error(val error: Throwable) : Result<Nothing>()
  object Loading : Result<Nothing>()

  override fun toString(): String {
    return when (this) {
      is Success<*> -> "Success[data=$data]"
      is Error -> "Error[exception=${error.message ?: "Error"}]"
      Loading -> "Loading"
    }
  }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
  get() = this is Result.Success && data != null
