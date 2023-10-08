package com.example.kasirukk.feature.cashier.transaction.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.login.ID_USER
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.databinding.FragmentCashierAddTransactionBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierMenuMakananAdapter
import com.example.kasirukk.feature.cashier.transaction.adapter.CashierMenuMinumanAdapter
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import dagger.hilt.android.AndroidEntryPoint
import korlibs.time.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CashierAddTransactionFragment : Fragment() {

    private var _binding: FragmentCashierAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val trxViewModel by viewModels<CashierViewModel>()
    private val mejaViewModel by viewModels<AdminMejaViewModel>()
    private val menuViewModel by viewModels<AdminMenuViewModel>()

    private lateinit var makananAdapter: CashierMenuMakananAdapter
    private lateinit var minumanAdapter: CashierMenuMinumanAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCashierAddTransactionBinding.inflate(inflater, container, false)
        var id_transaksi: Long = 1
        val getId = trxViewModel.getLastIdTransaction()

        if (getId != null){
            id_transaksi = getId+1
        }

        val setCurrentFragment = (activity as KasirActivity)

        val spinner = binding.fragmentCashierAddTransactionSpTable
        val buyer = binding.fragmentCashierAddTransactionEtName
        val note = binding.fragmentCashierAddTransactionEtNote
        var table = ""
        val unix = DateTime.invoke(DateTime.now().date)

        val spiner = ArrayAdapter<Any>(requireContext(),
            android.R.layout.simple_spinner_item)

        spiner.add("Pilih")
        CoroutineScope(Dispatchers.Main).launch {
            mejaViewModel.freeMejas.collect {
                data -> data.forEach {
                    spiner.add(it?.number.toString())
                }
            }
        }

        spiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spiner


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                table = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        initRvMakanan(id_transaksi)
        initRvMinuman(id_transaksi)



        binding.fragmentCashierAddTransactionBtnSave.setOnClickListener {

            when {

                buyer.text.isEmpty() -> alert()
                buyer.text.toString() == "" -> alert()
                table == "Pilih" -> alert()

                else -> {
                    val tableData = mejaViewModel.getMejaByNumber(table.toLong())!!
                    trxViewModel.insertTransaction(id_transaksi, unix, ID_USER, tableData.id_meja, buyer.text.toString(), note.text.toString(), false)
                    mejaViewModel.updateMeja(tableData.id_meja, table.toLong(), true)
                    setCurrentFragment.setCurrentFragment(CashierViewTransactionFragment())
                }
            }
       }

        binding.fragmentCashierAddTransactionIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(CashierViewTransactionFragment())
        }

        return binding.root
    }

    private fun initRvMinuman(id_transaksi: Long) {
        minumanAdapter = CashierMenuMinumanAdapter(id_transaksi, trxViewModel)
        val rvMinuman = binding.fragmentCashierAddTransactionRvMinuman
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
        val rvMakanan = binding.fragmentCashierAddTransactionRvMakanan
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

    private fun alert(){
        Toast.makeText(requireContext(), "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}