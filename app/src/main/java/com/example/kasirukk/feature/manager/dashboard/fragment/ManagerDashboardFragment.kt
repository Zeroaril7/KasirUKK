package com.example.kasirukk.feature.manager.dashboard.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.kasirukk.feature.manager.ManagerActivity
import com.example.kasirukk.feature.login.USER
import com.example.kasirukk.databinding.FragmentManagerDashboardBinding
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagerDashboardFragment : Fragment() {
    private var _binding: FragmentManagerDashboardBinding? = null
    private val binding get() = _binding!!

    private val menuViewModel by activityViewModels<AdminMenuViewModel>()
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagerDashboardBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as ManagerActivity)

        binding.fragmentManagerDashboardTvUser.text = "Welcome, $USER"

        binding.logout.setOnClickListener {
            setCurrentFragment.logout()
        }

        val favoriteMenu = menuViewModel.favoriteMenu

        pieChart = binding.fragmentManagerDashboardPieChart
        val pieList:ArrayList<PieEntry> = ArrayList()

        for (i in favoriteMenu.indices){
            pieList.add(PieEntry(favoriteMenu[i]?.sold?.toFloat()!!, favoriteMenu[i]?.name_menu))
        }

        val pieDataSet = PieDataSet(pieList,"")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        pieDataSet.valueTextColor= Color.WHITE
        pieDataSet.valueTextSize=15f

        val pieData = PieData(pieDataSet)

        pieChart.data= pieData

        pieChart.description.isEnabled = false

        pieChart.isDrawHoleEnabled = false

        pieChart.legend.isEnabled = true
        pieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        pieChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        pieChart.legend.isWordWrapEnabled = true
        
        pieChart.animateY(2000, Easing.EaseInOutQuad)

        pieChart.invalidate()

        return binding.root
    }
}
