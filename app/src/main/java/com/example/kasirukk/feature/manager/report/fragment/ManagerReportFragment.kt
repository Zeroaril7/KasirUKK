package com.example.kasirukk.feature.manager.report.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.manager.ManagerActivity
import com.example.kasirukk.R
import com.example.kasirukk.feature.login.USER
import com.example.kasirukk.databinding.FragmentManagerReportBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.example.kasirukk.feature.admin.user.viewmodel.AdminUserViewModel
import com.example.kasirukk.feature.cashier.viewmodel.CashierViewModel
import com.example.kasirukk.feature.manager.report.model.FilterModel
import com.example.kasirukk.feature.manager.report.adapter.ManagerReportAdapter
import com.example.kasirukk.feature.manager.report.viewmodel.ManagerReportViewModel
import com.ukk.data.User
import dagger.hilt.android.AndroidEntryPoint
import korlibs.time.DateTime
import korlibs.time.Month
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class ManagerReportFragment : Fragment() {

    private var _binding: FragmentManagerReportBinding? = null
    private val binding get() = _binding!!

    private val reportViewModel by activityViewModels<ManagerReportViewModel>()
    private val trxViewModel by activityViewModels<CashierViewModel>()
    private val userViewModel by activityViewModels<AdminUserViewModel>()
    private val mejaViewModel by activityViewModels<AdminMejaViewModel>()
    private lateinit var reportAdapter: ManagerReportAdapter
    private val filterModel: MutableList<FilterModel> = mutableListOf()
    private var trxData = ArrayList<FilterModel>()
    private var profit : Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagerReportBinding.inflate(inflater, container, false)

        var datePickerDialog : DatePickerDialog
        val spinnerUser = binding.fragmentManagerReportSpFilterUser
        val dataSpinnerUser = ArrayAdapter<Any>(requireContext(),
            R.layout.custom_array_item)
        val spinnerDaily = binding.fragmentManagerReportSpFilterDate
        var unix : DateTime? = null
        var startDay: DateTime? = null
        var endDay: DateTime? = null
        var dataUser : String? = null
        var dataDaily : String
        val setCurrentFragment = (activity as ManagerActivity)

        binding.fragmentManagerReportTvUser.text = "Welcome, $USER"

        binding.logout.setOnClickListener {
            setCurrentFragment.logout()
        }
//        User Spinner

        dataSpinnerUser.add("All")
        CoroutineScope(Dispatchers.Main).launch {
            userViewModel.kasirUsers.collect {
                    data -> data.forEach {
                dataSpinnerUser.add(it.name_user)
            }
            }
        }

        dataSpinnerUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUser.adapter = dataSpinnerUser

            spinnerUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    dataUser = spinnerUser.selectedItem.toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

//        Daily Spinner

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_daily,
            R.layout.custom_array_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDaily.adapter = adapter
        }


            spinnerDaily.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    dataDaily = spinnerDaily.selectedItem.toString()
                    val year = DateTime.now().year

                    if (dataDaily == "All"){
                        startDay = null
                        endDay = null
                        unix = null
                    }

                    if(dataDaily == "January"){
                        startDay = DateTime.invoke(year = year, month = Month.January, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.January, day = Month.January.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "February"){
                        startDay = DateTime.invoke(year = year, month = Month.February, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.February, day = Month.February.daysCommon)
                        unix = null
                    }

                    if(dataDaily == "March"){
                        startDay = DateTime.invoke(year = year, month = Month.March, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.March, day = Month.March.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "April"){
                        startDay = DateTime.invoke(year = year, month = Month.April, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.April, day = Month.April.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "May"){
                        startDay = DateTime.invoke(year = year, month = Month.May, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.May, day = Month.May.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "June"){
                        startDay = DateTime.invoke(year = year, month = Month.June, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.June, day = Month.June.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "July"){
                        startDay = DateTime.invoke(year = year, month = Month.July, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.July, day = Month.July.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "August"){
                        startDay = DateTime.invoke(year = year, month = Month.August, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.August, day = Month.August.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "September"){
                        startDay = DateTime.invoke(year = year, month = Month.September, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.September, day = Month.September.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "October"){
                        startDay = DateTime.invoke(year = year, month = Month.October, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.October, day = Month.October.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "November"){
                        startDay = DateTime.invoke(year = year, month = Month.November, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.November, day = Month.November.daysLeap)
                        unix = null
                    }

                    if(dataDaily == "December"){
                        startDay = DateTime.invoke(year = year, month = Month.December, day = 1)
                        endDay = DateTime.invoke(year = year, month = Month.December, day = Month.December.daysLeap)
                        unix = null
                    }

                    binding.fragmentManagerReportTvFilterDate.text = "All"
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
        }


//        Date

        binding.fragmentManagerReportIvFilterBgDate.setOnClickListener {
            val c = Calendar.getInstance()
            datePickerDialog = DatePickerDialog(requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    unix = DateTime.invoke(year = year, month = monthOfYear+1, day = dayOfMonth)
                    startDay = null
                    endDay = null
                    binding.fragmentManagerReportTvFilterDate.text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year },
                c[Calendar.YEAR],
                c[Calendar.MONTH],
                c[Calendar.DAY_OF_MONTH]
            )

            datePickerDialog.setOnCancelListener {

            }
            datePickerDialog.show()
            spinnerDaily.setSelection(0)
        }

        initAllTrx()

        binding.fragmentManagerReportBtnSearch.setOnClickListener {
            initRv(dataUser, unix, startDay, endDay)
        }

        return binding.root
    }

    private fun initAllTrx(){
        reportAdapter = ManagerReportAdapter(mejaViewModel)
        val rvReport = binding.fragmentManagerReportRv
        rvReport.setHasFixedSize(true)
        rvReport.layoutManager = LinearLayoutManager(requireContext())
        rvReport.adapter = reportAdapter

        val getProfit = reportViewModel.getAllReturn()
        profit = 0
        if (getProfit?.SUM != null){
            profit = getProfit.SUM
        }

        filterModel.clear()

        binding.fragmentManagerReportTvReturn.text = "Return: Rp. $profit"
        CoroutineScope(Dispatchers.Main).launch {
            trxViewModel.transactions.collect {
                    data ->
                    for (i in data.indices){
                        filterModel.add(FilterModel(created_at = data[i].created_at, buyer_name = data[i].buyer_name, is_paid = data[i].is_paid, id_meja = data[i].id_meja))
                    }

                reportAdapter.submitList(filterModel)
                rvReport.adapter = reportAdapter
            }
        }
    }

    private fun initRv(user: String?, date: DateTime?, startDay: DateTime?, endDay: DateTime?){

        filterModel.clear()

        val userData : User? = userViewModel.getUserByNameUser(user!!)

        reportAdapter = ManagerReportAdapter(mejaViewModel)
        val rvReport = binding.fragmentManagerReportRv
        rvReport.setHasFixedSize(true)
        rvReport.layoutManager = LinearLayoutManager(requireContext())
        rvReport.adapter = reportAdapter

        if (userData != null && date != null && startDay == null && endDay == null){
            CoroutineScope(Dispatchers.Main).launch {
                reportViewModel.getTrxByUserAndDate(userData.id_user, date)?.collect {
                    data ->
                    for (i in data.indices){
                        filterModel.add(FilterModel(created_at = data[i]!!.created_at, buyer_name = data[i]!!.buyer_name, is_paid = data[i]!!.is_paid, id_meja = data[i]!!.id_meja))
                    }
                    reportAdapter.submitList(filterModel)
                    rvReport.adapter = reportAdapter
                }
            }

            val getProfit = reportViewModel.getReturnByUserAndDate(userData!!.id_user, date)
            profit = 0
            if (getProfit?.SUM != null){
                profit = getProfit.SUM
            }

            binding.fragmentManagerReportTvReturn.text = "Return: Rp. $profit"
        }

        if(userData != null && startDay != null && endDay != null && date == null){
            CoroutineScope(Dispatchers.Main).launch {
                reportViewModel.getTrxByUserAndMonthly(userData.id_user, startDay, endDay)?.collect {
                        data ->

                        for (i in data.indices){
                            filterModel.add(FilterModel(created_at = data[i]!!.created_at, buyer_name = data[i]!!.buyer_name, is_paid = data[i]!!.is_paid, id_meja = data[i]!!.id_meja))
                        }
                        reportAdapter.submitList(filterModel)
                        rvReport.adapter = reportAdapter
                }
            }

            val getProfit = reportViewModel.getReturnByUserAndMonthly(userData.id_user, startDay, endDay)
            profit = 0
            if (getProfit?.SUM != null){
                profit = getProfit.SUM
            }

            binding.fragmentManagerReportTvReturn.text = "Return: Rp. $profit"
        }

        if(userData != null && date == null && startDay == null && endDay == null){
            CoroutineScope(Dispatchers.Main).launch {
                reportViewModel.getTrxByUser(userData.id_user).collect { data ->

                    for (i in data.indices){
                        filterModel.add(FilterModel(created_at = data[i]!!.created_at, buyer_name = data[i]!!.buyer_name, is_paid = data[i]!!.is_paid, id_meja = data[i]!!.id_meja))
                    }
                    reportAdapter.submitList(filterModel)
                    rvReport.adapter = reportAdapter
                }
            }

            val getProfit = reportViewModel.getReturnByUser(userData.id_user)
            profit = 0
            if (getProfit?.SUM != null){
                profit = getProfit.SUM
            }

            binding.fragmentManagerReportTvReturn.text = "Return: Rp. $profit"
        }

        if(startDay != null && endDay != null && userData == null && date == null){
            CoroutineScope(Dispatchers.Main).launch {
                reportViewModel.getTrxByMonthly(startDay, endDay).collect { data ->
                    for (i in data.indices){
                        filterModel.add(FilterModel(created_at = data[i]!!.created_at, buyer_name = data[i]!!.buyer_name, is_paid = data[i]!!.is_paid, id_meja = data[i]!!.id_meja))
                    }
                    reportAdapter.submitList(filterModel)
                    rvReport.adapter = reportAdapter
                }
            }

            val getProfit = reportViewModel.getReturnByMonthly(startDay, endDay)
            profit = 0
            if (getProfit?.SUM != null){
                profit = getProfit.SUM
            }

            binding.fragmentManagerReportTvReturn.text = "Return: Rp. $profit"
        }

        if(date != null && startDay == null && endDay == null && userData == null){
            CoroutineScope(Dispatchers.Main).launch {
                reportViewModel.getTrxByDate(date).collect { data ->
                    for (i in data.indices){
                        filterModel.add(FilterModel(created_at = data[i]!!.created_at, buyer_name = data[i]!!.buyer_name, is_paid = data[i]!!.is_paid, id_meja = data[i]!!.id_meja))
                    }
                    reportAdapter.submitList(filterModel)
                    rvReport.adapter = reportAdapter
                }
            }

            val getProfit = reportViewModel.getReturnByDate(date)
            profit = 0
            if (getProfit?.SUM != null){
                profit = getProfit.SUM
            }

            binding.fragmentManagerReportTvReturn.text = "Return: Rp. $profit"
        }

        if(date == null && startDay == null && endDay == null && userData == null){
            initAllTrx()
        }

    }
}