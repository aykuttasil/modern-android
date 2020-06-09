package com.aykuttasil.modernapp.clean

/*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aykuttasil.modernapp.di.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class UserFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory
  private val viewModel by viewModels<UserViewModel> { viewModelFactory }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel.liveUser.observe(viewLifecycleOwner, Observer {
      Timber.i(it.toString())
    })

    viewModel.initUser()
  }


}

 */