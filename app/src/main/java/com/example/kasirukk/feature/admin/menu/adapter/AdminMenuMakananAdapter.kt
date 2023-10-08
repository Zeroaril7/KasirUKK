package com.example.kasirukk.feature.admin.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.databinding.RvCardMenuBinding
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.ukk.data.Menu


class AdminMenuMakananAdapter(private val listener: ClickListener, private val menuViewModel: AdminMenuViewModel): ListAdapter<Menu, AdminMenuMakananAdapter.ViewHolder>(
    PRODUCTS_COMPARATOR
) {
    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(old: Menu, aNew: Menu): Boolean {

                if (old.type != aNew.type){
                    return false
                }

                if(old.description != aNew.description){
                    return false
                }

                if(old.price != aNew.price){
                    return false
                }

                return true
            }

            override fun areContentsTheSame(old: Menu, aNew: Menu): Boolean {
                return old == aNew
            }
        }
    }

    inner class ViewHolder(private val binding: RvCardMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(menu: Menu?, listener: ClickListener){
            binding.cardMenuTvTitle.text = menu?.name_menu
            binding.cardMenuTvDesc.text = menu?.description
            binding.cardMenuTvHarga.text = "Rp. " + menu?.price.toString()
            binding.cardMenuIvDelete.setOnClickListener {
                menuViewModel.deleteMenu(menu!!.id_menu)
            }
            binding.cardMenuIvUpdate.setOnClickListener {
                listener.onUpdateMakananItemClicked(menu!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminMenuMakananAdapter.ViewHolder {
        val binding = RvCardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminMenuMakananAdapter.ViewHolder, position: Int) {
        val menus = getItem(position)
        holder.bind(menus, listener)
    }


    interface ClickListener {
        fun onUpdateMakananItemClicked(item: Menu)
    }
}