package com.example.kasirukk.feature.admin.meja.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.databinding.RvCardTableBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.ukk.data.Meja


class AdminMejaAdapter(private val listener: ClickListener, private val mejaViewModel: AdminMejaViewModel): ListAdapter<Meja, AdminMejaAdapter.ViewHolder>(
    PRODUCTS_COMPARATOR
) {
    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<Meja>() {
            override fun areItemsTheSame(old: Meja, aNew: Meja): Boolean {

                if (old.number != aNew.number) {
                    return false
                }
                return true
            }

            override fun areContentsTheSame(old: Meja, aNew: Meja): Boolean {
                return old == aNew
            }
        }
    }

    inner class ViewHolder(private val binding: RvCardTableBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(meja: Meja?, listener: ClickListener){
            binding.cardTableTvTitle.text = "Meja " + meja?.number
            binding.cardTableIvDelete.setOnClickListener {
                mejaViewModel.deleteMeja(meja!!.id_meja)
            }
            binding.cardTableIvUpdate.setOnClickListener {
                listener.onUpdateItemClicked(meja!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminMejaAdapter.ViewHolder {
        val binding = RvCardTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminMejaAdapter.ViewHolder, position: Int) {
        val mejas = getItem(position)
        holder.bind(mejas, listener)
    }

    interface ClickListener {
        fun onUpdateItemClicked(item: Meja)
    }
}
