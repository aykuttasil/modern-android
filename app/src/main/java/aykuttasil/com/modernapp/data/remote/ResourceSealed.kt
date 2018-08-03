package aykuttasil.com.modernapp.data.remote

sealed class ResourceSealed<out T> {
    class Loading<out T> : ResourceSealed<T>()
    data class Success<out T>(val data: T?) : ResourceSealed<T>()
    data class Failure<out T>(val throwable: Throwable) : ResourceSealed<T>()
}