package com.example.kasirukk.feature.cashier.transaction.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.R
import com.example.kasirukk.databinding.RvCardMenuTransactionPaymentBinding
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.ukk.data.GetDetailTransaksiById

class CashierPaymentMenuAdapter(private val menuViewModel: AdminMenuViewModel) : ListAdapter<GetDetailTransaksiById, CashierPaymentMenuAdapter.ViewHolder>(PRODUCTS_COMPARATOR) {


    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<GetDetailTransaksiById>() {
            override fun areItemsTheSame(old: GetDetailTransaksiById, aNew: GetDetailTransaksiById): Boolean {
                return old.id_transaksi == aNew.id_transaksi
            }

            override fun areContentsTheSame(old: GetDetailTransaksiById, aNew: GetDetailTransaksiById): Boolean {
                return old == aNew
            }
        }
    }

    inner class ViewHolder(private val binding : RvCardMenuTransactionPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        val card = binding.cardMenuTransactionPaymentIvBg
        val line = binding.cardMenuTransactionPaymentLine

        fun bind(detailTransaksi: GetDetailTransaksiById){

            val menu = menuViewModel.getMenuById(detailTransaksi.id_menu)
            binding.cardMenuTransactionPaymentTvMenu.text = menu?.name_menu
            binding.cardMenuTransactionPaymentTvAmount.text = detailTransaksi.amount.toString()
            binding.cardMenuTransactionPaymentTvHarga.text = menu?.price.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CashierPaymentMenuAdapter.ViewHolder {
        val binding = RvCardMenuTransactionPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CashierPaymentMenuAdapter.ViewHolder, position: Int) {
        val menuTrx = getItem(position)
        holder.bind(menuTrx)

        if(position == itemCount - 1){
            holder.card.setBackgroundResource(R.drawable.card_background_bottom)
            holder.line.visibility = View.GONE
        }

        if (position == 0){
            holder.card.setBackgroundResource(R.drawable.card_background_top)
        }
    }
}