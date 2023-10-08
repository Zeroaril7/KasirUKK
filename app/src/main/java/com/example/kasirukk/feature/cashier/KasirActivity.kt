package com.example.kasirukk.feature.cashier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kasirukk.R
import com.example.kasirukk.databinding.ActivityKasirBinding
import com.example.kasirukk.feature.cashier.history.fragment.CashierViewHistoryFragment
import com.example.kasirukk.feature.cashier.transaction.fragment.CashierViewTransactionFragment
import com.example.kasirukk.feature.login.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KasirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKasirBinding
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKasirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.activityKasirBottomNavigationView

        val transactionFragment = CashierViewTransactionFragment()
        val historyFragment = CashierViewHistoryFragment()

        setCurrentFragment(transactionFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.kasir_transaction ->setCurrentFragment(transactionFragment)
                R.id.kasir_history ->setCurrentFragment(historyFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_kasir_flFragment, fragment)
            commit()
        }
    }

    fun logout(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}