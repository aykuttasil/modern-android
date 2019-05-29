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
package aykuttasil.com.modernapp.util.adapter

/**
 * A RecyclerView adapter for [Repo] class.
 */
/*
class RepoListAdapter(
  private val dataBindingComponent: DataBindingComponent,
  appExecutors: AppExecutors,
  private val showFullName: Boolean,
  private val repoClickCallback: ((Repo) -> Unit)?
) : DataBoundListAdapter<Repo, RepoItemBinding>(
  appExecutors = appExecutors,
  diffCallback = object : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
      return oldItem.owner == newItem.owner &&
          oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
      return oldItem.description == newItem.description
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
    binding.showFullName = showFullName
    binding.root.setOnClickListener {
      binding.repo?.let {
        repoClickCallback?.invoke(it)
      }
    }
    return binding
  }

  override fun bind(binding: RepoItemBinding, item: Repo) {
    binding.repo = item
  }
}
*/
