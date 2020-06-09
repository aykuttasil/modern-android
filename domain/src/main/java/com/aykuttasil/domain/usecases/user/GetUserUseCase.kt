package com.aykuttasil.domain.usecases.user

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.util.Optional
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
  suspend operator fun invoke(userName: String): Optional<UserEntity> {
    return Optional.of(userRepository.getUser(userName))
  }
}
