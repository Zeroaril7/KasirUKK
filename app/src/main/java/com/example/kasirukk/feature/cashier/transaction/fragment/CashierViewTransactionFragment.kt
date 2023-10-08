package com.example.kasirukk.feature.cashier.transaction.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.feature.login.USER
import com.example.kasirukk.databinding.FragmentCashierViewTransactionBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierTransactionAdapter
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import com.ukk.data.Transaksi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CashierViewTransactionFragment : Fragment(), CashierTransactionAdapter.ClickListener {

    private lateinit var trxAdapter: CashierTransactionAdapter

    private var _binding: FragmentCashierViewTransactionBinding? = null
    private val binding get() = _binding!!

    private val trxViewModel by activityViewModels<CashierViewModel>()
    private val mejaViewModel by activityViewModels<AdminMejaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashierViewTransactionBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as KasirActivity)

        binding.fragmentKasirViewTransactionTvUser.text = "Welcome, $USER"

        binding.logout.setOnClickListener {
            setCurrentFragment.logout()
        }

        initRv()

        binding.fragmentKasirViewTransactionFabAdd.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierAddTransactionFragment())
        }

        return binding.root
    }

    private fun initRv(){
        trxAdapter = CashierTransactionAdapter(this, mejaViewModel)
        val rvTrx = binding.fragmentKasirViewTransactionRv
        rvTrx.setHasFixedSize(true)
        rvTrx.layoutManager = LinearLayoutManager(requireContext())
        rvTrx.adapter = trxAdapter

        CoroutineScope(Dispatchers.Main).launch {
            trxViewModel.onGoingTransactions.collect {
                data -> trxAdapter.submitList(data)
                rvTrx.adapter = trxAdapter
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(item: Transaksi) {
        val setCurrentFragment = (activity as KasirActivity)
        setCurrentFragment.setCurrentFragment(CashierPaymentTransactionFragment(item))
    }

}