package com.aykuttasil.domain.usecases.user

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.usecases.UseCase
import com.aykuttasil.domain.util.Result
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) :
  UseCase<UserEntity, String>() {

  /*
  suspend operator fun invoke(userName: String): UserEntity? {
    return userRepository.getUser(userName)
  }

   */

  override suspend fun run(params: String): Result<UserEntity> {
    val user = userRepository.getUser(params) ?: return Result.Error("User is null!")
    return Result.Success(user)
  }

}
