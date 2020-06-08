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
package com.aykuttasil.modernapp.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.databinding.RepoItemBinding
import com.aykuttasil.modernapp.ui.common.DataBoundListAdapter
import com.aykuttasil.common.util.AppExecutors


data class RepoViewData(
    var repoId: Int?,
    var repoName: String?
)

class RepoListAdapter(
  private val dataBindingComponent: DataBindingComponent,
  appExecutors: AppExecutors,
  private val repoClickCallback: ((RepoViewData) -> Unit)?
) : DataBoundListAdapter<RepoViewData, RepoItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<RepoViewData>() {
      override fun areItemsTheSame(oldItem: RepoViewData, newItem: RepoViewData): Boolean {
        return oldItem.repoId == newItem.repoId
      }

      override fun areContentsTheSame(oldItem: RepoViewData, newItem: RepoViewData): Boolean {
        return oldItem.repoName == newItem.repoName
      }
    }
) {

  override fun createBinding(parent: ViewGroup): RepoItemBinding {
    val binding = DataBindingUtil.inflate<RepoItemBinding>(
        LayoutInflater.from(parent.context),
        R.layout.repo_item,
        parent,
        false,
        dataBindingComponent
    )
    binding.root.setOnClickListener {
      binding.viewData?.let {
        repoClickCallback?.invoke(it)
      }
    }
    return binding
  }

  override fun bind(binding: RepoItemBinding, item: RepoViewData) {
    binding.viewData = item
  }
}

