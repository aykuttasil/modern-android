package com.aykuttasil.domain.usecases.user

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
  private val userRepository: UserRepository
) {
  suspend operator fun invoke(user: UserEntity): Boolean {
    return userRepository.saveUser(user)
  }
}