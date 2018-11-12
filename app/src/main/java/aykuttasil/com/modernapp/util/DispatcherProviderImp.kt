package aykuttasil.com.modernapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImp : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val background: CoroutineDispatcher
        get() = Dispatchers.IO
}

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val background: CoroutineDispatcher
}
