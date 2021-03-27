package com.aykuttasil.modernapp.util

import com.aykuttasil.domain.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

class DispatcherProviderImp @Inject constructor() : DispatcherProvider {

  override val main: CoroutineDispatcher
    get() = Dispatchers.Main

  override val background: CoroutineDispatcher
    get() = Dispatchers.IO
}
