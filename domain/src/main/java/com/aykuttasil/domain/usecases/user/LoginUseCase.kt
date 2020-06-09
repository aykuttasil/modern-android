package com.aykuttasil.domain.usecases.user

/*
import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.usecases.UseCase
import com.aykuttasil.domain.util.Either
import com.aykuttasil.domain.util.Failure

class LoginUseCase(private val userRepository: UserRepository) :
  UseCase<UserEntity, LoginUseCase.LoginParams>() {

  data class LoginParams(
    var email: String,
    var pass: String
  )

  override suspend fun run(params: LoginParams): Either<Failure, UserEntity> {
    return try {
      val userEntity = userRepository.login(params)
      Either.Right(userEntity)
    } catch (e: Exception) {
      Either.Left(UserNotFoundFailure())
    }
  }

}

class UserNotFoundFailure : Failure.FeatureFailure()

 */