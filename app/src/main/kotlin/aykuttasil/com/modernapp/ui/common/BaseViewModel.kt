package aykuttasil.com.modernapp.ui.common

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import aykuttasil.com.modernapp.App
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel(app: App) : AndroidViewModel(app) {
  internal fun <T> launchOnViewModelScope(block: suspend () -> LiveData<T>): LiveData<T> {
    return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
      emitSource(block())
    }
  }
}
