package com.example.kasirukk.feature.cashier.history.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.feature.login.USER
import com.example.kasirukk.databinding.FragmentCashierViewHistoryBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.example.kasirukk.feature.cashier.history.adapter.CashierHistoryAdapter
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import com.ukk.data.Transaksi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CashierViewHistoryFragment : Fragment(), CashierHistoryAdapter.ClickListener {

    private lateinit var historyAdapter: CashierHistoryAdapter

    private var _binding: FragmentCashierViewHistoryBinding? = null
    private val binding get() = _binding!!

    private val trxViewModel by activityViewModels<CashierViewModel>()
    private val mejaViewModel by activityViewModels<AdminMejaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashierViewHistoryBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as KasirActivity)

        binding.fragmentKasirViewHistoryTvUser.text = "Welcome, $USER"

        binding.logout.setOnClickListener {
            setCurrentFragment.logout()
        }

        initRv()

        return binding.root
    }

    private fun initRv() {
        historyAdapter = CashierHistoryAdapter(this, mejaViewModel)
        val rvHistory = binding.fragmentKasirViewHistoryRv
        rvHistory.setHasFixedSize(true)
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        rvHistory.adapter = historyAdapter

        CoroutineScope(Dispatchers.Main).launch {
            trxViewModel.completeTransactions.collect {
                data -> historyAdapter.submitList(data)
                rvHistory.adapter = historyAdapter
            }
        }
    }

    override fun onItemClicked(item: Transaksi) {
        val setCurrentFragment = (activity as KasirActivity)
        setCurrentFragment.setCurrentFragment(CashierViewTransactionHistoryFragment(item))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}