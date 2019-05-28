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
package aykuttasil.com.modernapp.ui.main.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.databinding.FragmentMainBinding
import aykuttasil.com.modernapp.di.Injectable
import aykuttasil.com.modernapp.ui.common.BaseFragment
import aykuttasil.com.modernapp.util.delegates.Inflate
import com.aykutasil.modernapp.util.LogUtils
import com.aykutasil.modernapp.util.loadAsync
import com.aykutasil.modernapp.util.then
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.delay

class MainFragment : BaseFragment(), Injectable {

  private val binding: FragmentMainBinding by Inflate(R.layout.fragment_main)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnGoUserActivity.setOnClickListener {
      Navigation.findNavController(binding.btnGoUserActivity).navigate(R.id.action_mainFragment_to_aboutFragment)
    }

    loadAsync {
      LogUtils.i("Thread Name load 1:" + Thread.currentThread().name)
      delay(10000)
      LogUtils.i("Thread Name load 2:" + Thread.currentThread().name)

      val ad = "aykut"
      ad
    } then {
      LogUtils.i("Thread Name then:" + Thread.currentThread().name)
      LogUtils.i("Result: $it")
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    LogUtils.i("MainFragment > onDestroy")
  }
}
