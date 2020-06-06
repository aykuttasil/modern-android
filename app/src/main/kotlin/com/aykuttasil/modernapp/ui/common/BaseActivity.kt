package com.aykuttasil.modernapp.ui.common

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {
  protected inline fun <reified T : ViewDataBinding> binding(resId: Int): Lazy<T> =
      lazy { DataBindingUtil.setContentView<T>(this, resId) }
}
