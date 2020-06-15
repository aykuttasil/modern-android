package com.aykuttasil.modernapp.ui.common

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity : AppCompatActivity() {
  protected inline fun <reified T : ViewDataBinding> binding(resId: Int): Lazy<T> =
    lazy { DataBindingUtil.setContentView<T>(this, resId) }
}
