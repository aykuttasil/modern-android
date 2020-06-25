package com.aykuttasil.modernapp.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aykuttasil.modernapp.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

  private val viewModel by viewModels<LoginViewModel>()

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.liveLoginFormState.observe(viewLifecycleOwner, Observer {

    })
  }

  val afterTextChangedListener = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
      // ignore
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
      // ignore
    }

    override fun afterTextChanged(s: Editable) {
      viewModel.loginDataChanged(
        "", ""
        // usernameEditText.text.toString(),
        // passwordEditText.text.toString()
      )
    }
  }

}