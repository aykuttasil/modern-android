package aykuttasil.com.modernapp.util.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import aykuttasil.com.modernapp.data.local.entity.LocationEntity
import aykuttasil.com.modernapp.databinding.ItemLocationLayoutBinding

/**
 * Created by aykutasil on 29.12.2017.
 */
class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    var mList = arrayListOf<LocationEntity>()

    fun setList(list: ArrayList<LocationEntity>) {
        if (mList.size == 0) {
            mList = list
            notifyItemRangeChanged(0, mList.size)
        } else {
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = mList[oldItemPosition]
                    val newItem = mList[newItemPosition]

                    return oldItem.locationId == newItem.locationId
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

                    return oldItem.locTime == newItem.locTime
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

    fun addItem(item: LocationEntity) {
        mList.add(item)
        notifyItemInserted(mList.size)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder.create(parent)
    }

    class LocationViewHolder(private var binding: ItemLocationLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(locationEntity: LocationEntity) = with(binding) {
            location = locationEntity
            executePendingBindings()
        }

        companion object {
            fun create(parent: ViewGroup?): LocationViewHolder {
                val binding = ItemLocationLayoutBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
                return LocationViewHolder(binding)
            }
        }
    }
}