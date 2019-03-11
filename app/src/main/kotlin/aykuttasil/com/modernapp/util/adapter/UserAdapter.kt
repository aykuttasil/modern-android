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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import aykuttasil.com.modernapp.data.local.entity.UserEntity
import aykuttasil.com.modernapp.databinding.ItemUserLayoutBinding

/**
 * Created by aykutasil on 31.12.2017.
 */
class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

  var mList = arrayListOf<UserEntity>()

  fun setList(list: ArrayList<UserEntity>) {
    if (mList.size == 0) {
      mList = list
      notifyItemRangeChanged(0, mList.size)
    } else {
      val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
          val oldItem = mList[oldItemPosition]
          val newItem = mList[newItemPosition]

          return oldItem.UserId == newItem.UserId
        }

        override fun getOldListSize(): Int {
          return mList.size
        }

        override fun getNewListSize(): Int {
          return list.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
          val oldItem = mList[oldItemPosition]
          val newItem = mList[newItemPosition]

          return oldItem.UserName == newItem.UserName
        }

        /**
         * link areItemsTheSame = true ve areContentsTheSame = false dönerse,
         * yani row lar aynı item a ait ama içerik değişmiş ise
         * getChangePayload çağrılır. Ve burada item ın hangi alanı değişmiş ise belirlenerek bir Bundle a eklenir.
         * Eklenen Bundle onBindViewHolder da payloads argümanında yakalanarak sadece değişen alanın güncellenmesi sağlanır.
         * Örnek: Borsa uygulamasında anlık değişen fiyatları bu şekilde yakalayarak sadece ilgili alanın istediğimiz
         * şekilde değişmesini sağlayabiliriz.
         */
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
          return super.getChangePayload(oldItemPosition, newItemPosition)
        }
      })
      mList = list
      diffResult.dispatchUpdatesTo(this)
    }
  }

  fun addItem(item: UserEntity) {
    mList.add(item)
    notifyItemInserted(mList.size)
  }

  override fun getItemCount(): Int {
    return mList.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(mList[position])
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
    super.onBindViewHolder(holder, position, payloads)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.create(parent)
  }

  class ViewHolder(private var binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(UserEntity: UserEntity) = with(binding) {
      user = UserEntity
      executePendingBindings()
    }

    companion object {
      fun create(parent: ViewGroup?): ViewHolder {
        val binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return ViewHolder(binding)
      }
    }
  }
}
