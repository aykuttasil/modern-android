package aykuttasil.com.modernapp.clean

import javax.inject.Inject

class UserInteractions @Inject constructor(
  var login: Login,
  var getUser: GetUser
)

class Login @Inject constructor(var userRepository: UserRepository) {
  suspend operator fun invoke(user: User): User {
    userRepository.addUser(user)
    return userRepository.getUserList().first()
  }
}

class GetUser @Inject constructor(var userRepository: UserRepository) {
  suspend operator fun invoke(): User {
    return userRepository.getUser()
  }
}