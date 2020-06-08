package com.aykuttasil.domain.usecases.login

import com.aykuttasil.domain.usecases.UseCase
import com.aykuttasil.domain.util.Either
import com.aykuttasil.domain.util.Failure

class DoLoginUseCase : UseCase<Boolean,String>() {
  override suspend fun run(params: String): Either<Failure, Boolean> {
    return Either.Left(Failure.ServerError)
  }

}
