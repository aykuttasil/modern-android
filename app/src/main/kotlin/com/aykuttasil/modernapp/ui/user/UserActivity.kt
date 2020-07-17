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
package com.aykuttasil.modernapp.ui.user

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.aykuttasil.domain.util.logd
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.databinding.ActivityUserBinding
import com.aykuttasil.modernapp.ui.common.BaseActivity
import com.aykuttasil.modernapp.util.delegates.contentView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserActivity : BaseActivity() {

  private val viewModel by viewModels<UserViewModel>()

  private val binding: ActivityUserBinding by contentView(R.layout.activity_user)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    logd { "onCreate" }

    viewModel.viewState.onEach {
      when (it) {
        is UserViewModel.ViewState.Loading -> {
          Toast.makeText(this, "Lütfen Bekleyiniz", Toast.LENGTH_SHORT).show()
        }
        is UserViewModel.ViewState.Success -> {
          Toast.makeText(this, "İşlem Tamamlandı", Toast.LENGTH_SHORT).show()
          Toast.makeText(this, it.user.userName, Toast.LENGTH_SHORT).show()
        }
        is UserViewModel.ViewState.Error -> {
          Toast.makeText(this, it.err.message, Toast.LENGTH_SHORT).show()
        }
      }
    }.launchIn(lifecycleScope)

    viewModel.getUser()

  }
}
