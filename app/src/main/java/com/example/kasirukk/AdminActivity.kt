package com.example.kasirukk

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kasirukk.databinding.ActivityAdminBinding
import com.example.kasirukk.feature.admin.menu.fragment.AdminViewMenuFragment
import com.example.kasirukk.feature.admin.user.fragment.AdminViewUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.bottomNavigationView

        val userFragment = AdminViewUserFragment()
        val menuFragment = AdminViewMenuFragment()

        setCurrentFragment(userFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.admin_user-> setCurrentFragment(userFragment)
                R.id.admin_menu->setCurrentFragment(menuFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}