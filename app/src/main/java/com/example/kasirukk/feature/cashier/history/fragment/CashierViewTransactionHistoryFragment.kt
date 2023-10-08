package com.example.kasirukk.feature.cashier.history.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.databinding.FragmentCashierViewTransactionHistoryBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierPaymentBillAdapter
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierPaymentMenuAdapter
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import com.ukk.data.Transaksi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CashierViewTransactionHistoryFragment(private val transaksi: Transaksi) : Fragment() {
    private var _binding: FragmentCashierViewTransactionHistoryBinding? = null
    private val binding get() = _binding!!

    private val trxViewModel by viewModels<CashierViewModel>()
    private val mejaViewModel by viewModels<AdminMejaViewModel>()
    private val menuViewModel by viewModels<AdminMenuViewModel>()

    private lateinit var paymentBillAdapter : CashierPaymentBillAdapter
    private lateinit var paymentMenuAdapter: CashierPaymentMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashierViewTransactionHistoryBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as KasirActivity)

        val idTransaksi = transaksi.id_transaksi
        val dataTransaksi = trxViewModel.getTransactionById(idTransaksi)!!
        val dataTable = mejaViewModel.getMejaById(dataTransaksi.id_meja)!!
        val totalBill = trxViewModel.getBill(idTransaksi)

        binding.fragmentCashierTransactionHistoryTvTitle.text = "Order#$idTransaksi"
        binding.fragmentCashierTransactionHistoryTvBuyer.text = dataTransaksi.buyer_name
        binding.fragmentCashierTransactionHistoryTvTable.text = "Meja ${dataTable.number}"
        binding.fragmentCashierTransactionHistoryTvTotalBill.text  = "Rp. ${totalBill.bill}"


        if(dataTransaksi.note!!.isNotEmpty()){
            binding.fragmentCashierTransactionHistoryTvNoteDesc.text = dataTransaksi.note
        } else {
            binding.fragmentCashierTransactionHistoryIvNote.visibility = View.GONE
            binding.fragmentCashierTransactionHistoryIvLineNote.visibility = View.GONE
            binding.fragmentCashierTransactionHistoryTvNoteTitle.visibility = View.GONE
            binding.fragmentCashierTransactionHistoryTvNoteDesc.visibility = View.GONE
        }

        initRvMenu(idTransaksi)
        initRvBill(idTransaksi)

        binding.fragmentCashierTransactionHistoryIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierViewHistoryFragment())
        }

        return binding.root
    }
    private fun initRvMenu(id_transasksi: Long){
        paymentMenuAdapter = CashierPaymentMenuAdapter(menuViewModel)
        val rvMenu = binding.fragmentCashierTransactionHistoryRvMenu
        rvMenu.setHasFixedSize(true)
        rvMenu.layoutManager = LinearLayoutManager(requireContext())
        rvMenu.adapter = paymentMenuAdapter

        CoroutineScope(Dispatchers.Main).launch {
            trxViewModel.getDetailTransactionById(id_transasksi).collect {
                    data -> paymentMenuAdapter.submitList(data)
                rvMenu.adapter = paymentMenuAdapter
            }
        }
    }

    private fun initRvBill(id_transasksi: Long){
        paymentBillAdapter = CashierPaymentBillAdapter()
        val rvBill = binding.fragmentCashierTransactionHistoryRvBill
        rvBill.setHasFixedSize(true)
        rvBill.layoutManager = LinearLayoutManager(requireContext())
        rvBill.adapter = paymentBillAdapter

        CoroutineScope(Dispatchers.Main).launch {
            trxViewModel.getOrder(id_transasksi).collect {
                    data -> paymentBillAdapter.submitList(data)
                rvBill.adapter = paymentBillAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}