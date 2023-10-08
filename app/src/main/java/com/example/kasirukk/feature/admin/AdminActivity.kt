package com.example.kasirukk.feature.admin

import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kasirukk.R
import com.example.kasirukk.databinding.ActivityAdminBinding
import com.example.kasirukk.feature.admin.meja.fragment.AdminViewMejaFragment
import com.example.kasirukk.feature.admin.menu.fragment.AdminViewMenuFragment
import com.example.kasirukk.feature.admin.user.fragment.AdminViewUserFragment
import com.example.kasirukk.feature.login.MainActivity
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

        bottomNavigationView = binding.activityAdminBottomNavigationView

        val userFragment = AdminViewUserFragment()
        val menuFragment = AdminViewMenuFragment()
        val mejaFragment = AdminViewMejaFragment()

        setCurrentFragment(userFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.admin_user ->setCurrentFragment(userFragment)
                R.id.admin_menu ->setCurrentFragment(menuFragment)
                R.id.admin_meja ->setCurrentFragment(mejaFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_admin_flFragment, fragment)
            commit()
        }
    }

    fun logout(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}