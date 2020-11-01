package com.aykuttasil.domain.usecases.user

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.usecases.UseCase
import com.aykuttasil.domain.util.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetUserUseCase @Inject constructor(
  private val userRepository: UserRepository,
  private val dispatcherProvider: DispatcherProvider
) : UseCase<UserEntity?, String>(dispatcherProvider) {

  override suspend fun run(params: String): UserEntity? {
    userRepository.deleteUser()
    return userRepository.getUser(params)
  }
}
