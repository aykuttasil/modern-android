package com.aykuttasil.modernapp.ui.login

import com.aykuttasil.domain.entities.UserEntity

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
  val success: UserEntity? = null,
  val errorMessage: String? = null
)