package com.aykuttasil.modernapp.ui.main.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.databinding.FragmentMainBinding
import com.aykuttasil.modernapp.ui.common.BaseFragment
import com.aykuttasil.modernapp.ui.widget.bottomdialog.MyBottomDialog
import com.aykuttasil.modernapp.util.delegates.Inflate
import com.aykuttasil.modernapp.util.loadAsync
import com.aykuttasil.modernapp.util.then
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : BaseFragment() {

  private val binding: FragmentMainBinding by Inflate(R.layout.fragment_main)

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.btnGoUserActivity.setOnClickListener {
      findNavController().navigate(R.id.action_global_userActivity)
    }

    binding.btnOpenDialog.setOnClickListener {
      val dialog = MyBottomDialog()
      dialog.show(childFragmentManager, MyBottomDialog::class.java.simpleName)
    }

    loadAsync {
      Timber.i("Thread Name load 1:%s", Thread.currentThread().name)
      delay(10000)
      Timber.i("Thread Name load 2:%s", Thread.currentThread().name)
      "Aykut"
    } then {
      Timber.i("Thread Name then:%s", Thread.currentThread().name)
      Timber.i("Result: $it")
    }

  }
}
