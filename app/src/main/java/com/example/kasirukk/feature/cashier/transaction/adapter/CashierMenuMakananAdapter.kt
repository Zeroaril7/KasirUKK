package com.example.kasirukk.feature.cashier.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.databinding.RvCardMenuTransactionBinding
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import com.ukk.data.Detail_transaksi
import com.ukk.data.Menu

class CashierMenuMakananAdapter(private val id_transaksi: Long, private val trxViewModel: CashierViewModel): ListAdapter<Menu, CashierMenuMakananAdapter.ViewHolder>(PRODUCTS_COMPARATOR) {

    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(old: Menu, aNew: Menu): Boolean {
                return old.name_menu == aNew.name_menu
            }

            override fun areContentsTheSame(old: Menu, aNew: Menu): Boolean {
                return old == aNew
            }
        }
    }

    inner class ViewHolder(private val binding: RvCardMenuTransactionBinding) : RecyclerView.ViewHolder(binding.root){
        private var count = 0
        fun bind(menu: Menu?, detailTrx: Detail_transaksi?){

            if(detailTrx != null){
                count = detailTrx.amount.toInt()
            }

            binding.cardMenuTransactionTvTitle.text = menu?.name_menu
            binding.cardMenuTransactionTvDesc.text = menu?.description
            binding.cardMenuTransactionTvHarga.text = "Rp. " + menu?.price.toString()
            binding.cardMenuTransactionTvAmount.text = count.toString()
            binding.cardMenuTransactionIvPlus.setOnClickListener {
                count++
                binding.cardMenuTransactionTvAmount.text = count.toString()
                if (menu != null) {
                    when(count){
                        1 -> trxViewModel.insertDetailTransaction(id_transaksi, menu.id_menu, count.toLong(), (menu.price * count))
                        else -> trxViewModel.updateDetailTransaksi(id_menu = menu.id_menu, amount = count.toLong(), (menu.price * count))
                    }
                }
            }
            binding.cardMenuTransactionIvMinus.setOnClickListener {

                if (count > 0){
                    count--
                    binding.cardMenuTransactionTvAmount.text = count.toString()
                }

                if (menu != null) {
                    when(count){
                        0 -> trxViewModel.deleteDetailTransaction(menu.id_menu)
                        else -> trxViewModel.updateDetailTransaksi(id_menu = menu.id_menu, amount = count.toLong(), (menu.price * count))
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardMenuTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (i in 0..position) {
            val menus = getItem(position)
            val detailTrx = trxViewModel.getMenuData(id_transaksi, menus.id_menu)
            holder.bind(menus, detailTrx)
        }
    }
}