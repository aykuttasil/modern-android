package com.aykuttasil.domain.usecases.user

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
  suspend operator fun invoke(userName: String): UserEntity? {
    return userRepository.getUser(userName)
  }
}
