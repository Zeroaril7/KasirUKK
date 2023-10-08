package com.example.kasirukk.feature.manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kasirukk.R
import com.example.kasirukk.databinding.ActivityManagerBinding
import com.example.kasirukk.feature.login.MainActivity
import com.example.kasirukk.feature.manager.dashboard.fragment.ManagerDashboardFragment
import com.example.kasirukk.feature.manager.report.fragment.ManagerReportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagerBinding
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.activityManagerBottomNavigationView

        val dashboardFragment = ManagerDashboardFragment()
        val reportFragment = ManagerReportFragment()

        setCurrentFragment(dashboardFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.manager_dashboard -> setCurrentFragment(dashboardFragment)
                R.id.manager_report -> setCurrentFragment(reportFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_manager_flFragment, fragment)
            commit()
        }
    }

    fun logout(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}