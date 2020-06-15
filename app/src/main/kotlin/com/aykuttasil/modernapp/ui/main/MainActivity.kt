package com.aykuttasil.modernapp.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.ui.common.BaseActivity
import com.aykuttasil.modernapp.util.edit
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  @Inject
  lateinit var sharedPreference: SharedPreferences

  private val viewModel by viewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sharedPreference.edit {
      it.putString("test", "test")
    }
  }
}
