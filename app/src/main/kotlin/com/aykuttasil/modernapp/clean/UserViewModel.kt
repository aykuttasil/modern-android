package com.aykuttasil.modernapp.clean

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private var app: Application,
    private var userInteractions: UserInteractions
) : AndroidViewModel(app) {

  val liveUser: MutableLiveData<User> = MutableLiveData()

  fun initUser() {
    viewModelScope.launch {
      liveUser.postValue(userInteractions.getUser())
    }
  }

}