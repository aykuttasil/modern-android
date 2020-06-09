/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import kotlinx.coroutines.delay
import timber.log.Timber

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
