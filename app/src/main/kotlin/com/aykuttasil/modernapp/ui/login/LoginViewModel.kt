package com.aykuttasil.modernapp.ui.login

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aykuttasil.domain.usecases.user.LoginParams
import com.aykuttasil.domain.usecases.user.LoginUseCase
import com.aykuttasil.domain.util.Resource
import com.aykuttasil.modernapp.App
import com.aykuttasil.modernapp.ui.common.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class LoginViewModel @ViewModelInject constructor(
  val app: App,
  val loginUseCase: LoginUseCase
) : BaseViewModel(app) {

  private val _loginForm = MutableLiveData<LoginFormState>()
  val liveLoginFormState: LiveData<LoginFormState> = _loginForm

  private val _loginResult = MutableLiveData<LoginResult>()
  val liveLoginResult: LiveData<LoginResult> = _loginResult

  fun login(username: String, password: String) {
    loginDataChanged(username, password)
    val isValid = liveLoginFormState.value?.isDataValid ?: false

    viewModelScope.launch {
      loginUseCase(LoginParams(username, password)) { state ->
        state.onEach {
          when (it) {
            is Resource.Success -> {
              _loginResult.value =
                LoginResult(success = it.data)
            }
            is Resource.Error -> {
              _loginResult.value = LoginResult(errorMessage = it.error?.message ?: "HATA")
            }
            is Resource.Loading -> {

            }
          }
        }
      }
    }
  }

  fun loginDataChanged(username: String, password: String) {
    if (!isUserNameValid(username)) {
      _loginForm.value = LoginFormState(usernameError = "Invalid username")
    } else if (!isPasswordValid(password)) {
      _loginForm.value = LoginFormState(passwordError = "Invalid password")
    } else {
      _loginForm.value = LoginFormState(isDataValid = true)
    }
  }

  // A placeholder username validation check
  private fun isUserNameValid(username: String): Boolean {
    return if (username.contains("@")) {
      Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
      username.isNotBlank()
    }
  }

  // A placeholder password validation check
  private fun isPasswordValid(password: String): Boolean {
    return password.length > 5
  }


}