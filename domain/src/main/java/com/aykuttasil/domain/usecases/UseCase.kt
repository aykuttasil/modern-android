package com.aykuttasil.domain.usecases

import com.aykuttasil.domain.util.DispatcherProvider
import com.aykuttasil.domain.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import timber.log.Timber


typealias UseCaseCallback<Type> = (StateFlow<Resource<Type>>) -> Unit

@ExperimentalCoroutinesApi
abstract class UseCase<Type, in Params>(
  private val appDispatchers: DispatcherProvider
) where Type : Any? {

  private val _state = MutableStateFlow<Resource<Type>>(Resource.Loading())
  private val state: StateFlow<Resource<Type>>
    get() = _state

  private var useCaseCallback: UseCaseCallback<Type>? = null

  abstract suspend fun run(params: Params): Type

  private fun updateState(newState: Resource<Type>) {
    _state.value = newState
  }

  suspend operator fun invoke(
    params: Params,
    onResult: UseCaseCallback<Type>? = null
  ) {
    withContext(appDispatchers.main) {
      if (useCaseCallback == null) {
        useCaseCallback = onResult
        useCaseCallback?.invoke(state)
      }

      try {
        updateState(Resource.Loading())
        val result = withContext(appDispatchers.background) { run(params) }
        updateState(Resource.Success(result))
      } catch (ex: Exception) {
        Timber.e(ex)
        updateState(Resource.Error(ex))
      }
    }
  }
}
