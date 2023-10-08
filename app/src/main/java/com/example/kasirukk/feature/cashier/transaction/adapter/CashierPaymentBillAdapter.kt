package com.example.kasirukk.feature.cashier.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.databinding.RvCardBillTransactionBinding
import com.ukk.data.GetOrder

class CashierPaymentBillAdapter : ListAdapter<GetOrder, CashierPaymentBillAdapter.ViewHolder>(PRODUCTS_COMPARATOR) {

    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<GetOrder>() {
            override fun areItemsTheSame(old: GetOrder, aNew: GetOrder): Boolean {
                return old.name_menu == aNew.name_menu
            }

            override fun areContentsTheSame(old: GetOrder, aNew: GetOrder): Boolean {
                return old == aNew
            }
        }
    }

    inner class ViewHolder(private val binding: RvCardBillTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getOrder: GetOrder){
            binding.cardBillTransactionTvMenu.text = getOrder.name_menu
            binding.cardBillTransactionTvPrice.text = getOrder.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardBillTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val getOrder = getItem(position)
        holder.bind(getOrder)
    }
}