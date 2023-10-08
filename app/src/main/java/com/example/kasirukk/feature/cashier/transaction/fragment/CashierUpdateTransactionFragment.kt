package com.example.kasirukk.feature.cashier.transaction.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.databinding.FragmentCashierUpdateTransactionBinding
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierMenuMakananAdapter
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierMenuMinumanAdapter
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import com.ukk.data.Transaksi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CashierUpdateTransactionFragment(private val transaksi: Transaksi) : Fragment() {

    private var _binding: FragmentCashierUpdateTransactionBinding? = null
    private val binding get() = _binding!!

    private val trxViewModel by viewModels<CashierViewModel>()
    private val menuViewModel by viewModels<AdminMenuViewModel>()

    private lateinit var makananAdapter: CashierMenuMakananAdapter
    private lateinit var minumanAdapter: CashierMenuMinumanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashierUpdateTransactionBinding.inflate(inflater, container, false)
        val setCurrentFragment = (activity as KasirActivity)

        val note =  binding.fragmentCashierUpdateTransactionEtNote

        note.setText(transaksi.note)

        initRvMakanan(transaksi.id_transaksi)
        initRvMinuman(transaksi.id_transaksi)

        binding.fragmentCashierUpdateTransactionBtnSave.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierPaymentTransactionFragment(transaksi))
            trxViewModel.updateNoteTransaction(transaksi.id_transaksi, note.text.toString())
        }

        return binding.root
    }

    private fun initRvMinuman(id_transaksi: Long) {
        minumanAdapter = CashierMenuMinumanAdapter(id_transaksi, trxViewModel)
        val rvMinuman = binding.fragmentCashierUpdateTransactionRvMinuman
        rvMinuman.setHasFixedSize(true)
        rvMinuman.layoutManager = LinearLayoutManager(requireContext())
        rvMinuman.adapter = minumanAdapter

        CoroutineScope(Dispatchers.Main).launch {
            menuViewModel.menuMinuman.collect {
                    data -> minumanAdapter.submitList(data)
                rvMinuman.adapter = minumanAdapter
            }
        }
    }

    private fun initRvMakanan(id_transaksi: Long){
        makananAdapter = CashierMenuMakananAdapter(id_transaksi, trxViewModel)
        val rvMakanan = binding.fragmentCashierUpdateTransactionRvMakanan
        rvMakanan.setHasFixedSize(true)
        rvMakanan.layoutManager = LinearLayoutManager(requireContext())
        rvMakanan.adapter = makananAdapter

        CoroutineScope(Dispatchers.Main).launch {
            menuViewModel.menuMakanan.collect {
                    data -> makananAdapter.submitList(data)
                rvMakanan.adapter = makananAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}