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
package com.aykuttasil.modernapp.ui.user

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aykuttasil.modernapp.util.SingleLiveEvent
import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.usecases.user.GetUserUseCase
import com.aykuttasil.domain.util.Result
import com.aykuttasil.modernapp.App
import com.aykuttasil.modernapp.ui.common.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel @ViewModelInject constructor(
  @Assisted private val savedStateHandle: SavedStateHandle,
  private val app: App,
  private val getUserUseCase: GetUserUseCase
) : BaseViewModel(app) {

  val viewState = MutableLiveData<UserActivityViewState>()
  var errorState: SingleLiveEvent<Throwable?> =
    SingleLiveEvent()

  init {
    val viewState = UserActivityViewState()
    this.viewState.value = viewState
  }

  fun getUser() {
    viewModelScope.launch {
      try {
        viewState.value = viewState.value?.copy(isLoading = true)
        delay(1000)

        getUserUseCase("aykuttasil123") {
          when (it) {
            is Result.Success -> {
              viewState.value = viewState.value?.copy(isLoading = false, userEntity = it.data)
            }
            is Result.Error -> {
              Timber.e(it.msg)

              viewState.value =
                viewState.value?.copy(
                  isLoading = true,
                  userEntity = UserEntity(userName = "HOHOHOHO")
                )
            }
            else -> {
            }
          }
        }
      } catch (ex: Exception) {
        errorState.value = ex
      }
    }
  }

}

data class UserActivityViewState(
  var isLoading: Boolean = true,
  var userEntity: UserEntity? = null,
  var error: String? = null
)
