package com.aykuttasil.domain.usecases

import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
import com.aykuttasil.domain.util.Either
import com.aykuttasil.domain.util.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

 */


/*
/**
 * Created by Yossi Segev on 11/11/2017.
 */
abstract class UseCase<T>(private val transformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    fun observable(withData: Map<String, Any>? = null): Observable<T> {
        return createObservable(withData).compose(transformer)
    }
}
 */

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
/*
abstract class UseCase<out Type, in Params> where Type : Any {

  abstract suspend fun run(params: Params): Either<Failure, Type>

  operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
    val job = GlobalScope.async(Dispatchers.IO) { run(params) }
    GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
  }

  class None
}

 */


abstract class UseCase<out Type, in Params> where Type : Any {

  abstract suspend fun run(params: Params): Result<Type>

  suspend operator fun invoke(params: Params, onResult: (Result<Type>) -> Unit = {}) {
    withContext(Dispatchers.Main) {
      onResult(
        withContext(Dispatchers.IO) {
          run(params)
        }
      )
    }
  }
}


/*
class XUseCase() : UseCase<String, String>() {
  override suspend fun run(params: String): Result<String> {
    return Result.Error(Exception())
  }
}

suspend fun callXUseCase() {
  val useCaseX = XUseCase()
  useCaseX("") {
    when (it) {
      is Result.Error -> {
        it.error
      }
      is Result.Success -> {
        it.data
      }
      Result.Loading -> {
        it.toString()
      }
    }
  }
}

 */