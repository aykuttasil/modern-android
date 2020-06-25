package com.aykuttasil.modernapp.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
  val usernameError: String? = null,
  val passwordError: String? = null,
  val isDataValid: Boolean = false
)