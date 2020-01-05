package aykuttasil.com.modernapp.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.di.ViewModelFactory
import aykuttasil.com.modernapp.ui.common.BaseActivity
import com.aykutasil.modernapp.util.edit
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var sharedPreference: SharedPreferences

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private val viewModel by viewModels<MainViewModel> { viewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    sharedPreference.edit {
      it.putString("test", "test")
    }
  }
}
