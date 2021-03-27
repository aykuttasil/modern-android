package com.aykuttasil.domain.usecases.user

import com.aykuttasil.domain.entities.UserEntity
import com.aykuttasil.domain.repositories.UserRepository
import com.aykuttasil.domain.usecases.UseCase
import com.aykuttasil.domain.util.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

data class LoginParams(
    var username: String,
    var password: String
)

@ExperimentalCoroutinesApi
class LoginUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val userRepository: UserRepository
) : UseCase<UserEntity?, LoginParams>(dispatcherProvider) {

    override suspend fun run(params: LoginParams): UserEntity? {
        return userRepository.login(params)
    }

}