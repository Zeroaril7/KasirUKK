package com.example.kasirukk.feature.cashier.transaction.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.databinding.FragmentCashierPaymentTransactionBinding
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
class CashierPaymentTransactionFragment(private val transaksi: Transaksi) : Fragment(){

    private var _binding: FragmentCashierPaymentTransactionBinding? = null
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
        _binding = FragmentCashierPaymentTransactionBinding.inflate(inflater, container, false )
        val setCurrentFragment = (activity as KasirActivity)

        val idTransaksi = transaksi.id_transaksi
        val dataTransaksi = trxViewModel.getTransactionById(idTransaksi)!!
        val dataTable = mejaViewModel.getMejaById(dataTransaksi.id_meja)!!
        val totalBill = trxViewModel.getBill(idTransaksi)

        binding.fragmentCashierPaymentTransactionTvTitle.text = "Order#$idTransaksi"
        binding.fragmentCashierPaymentTransactionTvBuyer.text = dataTransaksi.buyer_name
        binding.fragmentCashierPaymentTransactionTvTable.text = "Meja ${dataTable.number}"
        binding.fragmentCashierPaymentTransactionTvTotalBill.text  = "Rp. ${totalBill.bill}"


        if(dataTransaksi.note!!.isNotEmpty()){
            binding.fragmentCashierPaymentTransactionTvNoteDesc.text = dataTransaksi.note
        } else {
            binding.fragmentCashierPaymentTransactionIvNote.visibility = View.GONE
            binding.fragmentCashierPaymentTransactionIvLineNote.visibility = View.GONE
            binding.fragmentCashierPaymentTransactionTvNoteTitle.visibility = View.GONE
            binding.fragmentCashierPaymentTransactionTvNoteDesc.visibility = View.GONE
        }


        initRvMenu(idTransaksi)
        initRvBill(idTransaksi)

        binding.fragmentCashierPaymentTransactionIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierViewTransactionFragment())
        }

        binding.fragmentCashierPaymentTransactionBtnSave.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierViewTransactionFragment())
            mejaViewModel.updateMeja(dataTable.id_meja, dataTable.number, false)
            trxViewModel.updateTransaction(idTransaksi, true)
        }

        binding.fragmentCashierPaymentTransactionIbUpdate.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierUpdateTransactionFragment(transaksi))
        }
        return binding.root
    }

    private fun initRvMenu(id_transasksi: Long){
        paymentMenuAdapter = CashierPaymentMenuAdapter(menuViewModel)
        val rvMenu = binding.fragmentCashierPaymentTransactionRvMenu
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
        val rvBill = binding.fragmentCashierPaymentTransactionRvBill
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