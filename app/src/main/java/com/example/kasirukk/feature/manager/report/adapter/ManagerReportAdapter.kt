package com.example.kasirukk.feature.manager.report.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.R
import com.example.kasirukk.databinding.RvCardTransactionBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.example.kasirukk.feature.manager.report.model.FilterModel
import korlibs.time.DateTimeTz

class ManagerReportAdapter(private val mejaViewModel: AdminMejaViewModel) : ListAdapter<FilterModel, ManagerReportAdapter.ViewHolder>(
    PRODUCTS_COMPARATOR) {

    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<FilterModel>() {
            override fun areItemsTheSame(old: FilterModel, aNew: FilterModel): Boolean {
                return old.created_at == aNew.created_at
            }

            override fun areContentsTheSame(old: FilterModel, aNew: FilterModel): Boolean {
                return old == aNew
            }
        }
    }

    inner class ViewHolder(private val binding: RvCardTransactionBinding)
        : RecyclerView.ViewHolder(binding.root)
    {

        fun bind(transaksi: FilterModel?){

            val offset = transaksi?.created_at?.localOffset
            val parseDate = DateTimeTz.utc(transaksi!!.created_at, offset!!).format("dd/MM/yyyy")
            val getNumberTable =  mejaViewModel.getMejaById(transaksi.id_meja)

            if(transaksi.is_paid){
                binding.cardTransactionIvStatusBg.setBackgroundResource(R.drawable.card_background_complete)
                binding.cardTransactionTvStatus.text = "Complete"
                binding.cardTransactionTvStatus.setTextColor(Color.parseColor("#60A020"))
            }

            binding.cardTrasactionTvDate.text = parseDate
            binding.cardTransactionTvBuyer.text = transaksi.buyer_name
            binding.cardTransactionTvTable.text = "Table ${getNumberTable?.number}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trxs = getItem(position)
        holder.bind(trxs)
    }

}