package aykuttasil.com.modernapp.util.livedata

import androidx.lifecycle.LiveData

/**
 * Helper class for transmitting an empty LiveData - Pretty useful!
 */
class AbsentLiveData<T> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {

            return AbsentLiveData()
        }
    }
}