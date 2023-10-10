package com.example.kasirukk.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.feature.cashier.KasirActivity
import com.example.kasirukk.feature.manager.ManagerActivity
import com.example.kasirukk.databinding.ActivityMainBinding
import com.example.kasirukk.feature.admin.user.viewmodel.AdminUserViewModel
import dagger.hilt.android.AndroidEntryPoint

var USER : String = ""
var ID_USER : Long = 0

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel by viewModels<AdminUserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainBtnLogin.setOnClickListener {

            if(isValid()){
                val username = binding.activityMainUsername.text.toString()
                val data = userViewModel.getUserByUsername(username)

                ID_USER = data!!.id_user
                USER = data.name_user

                when(data.role){
                    "Admin" -> {
                        startActivity(Intent(this, AdminActivity::class.java))
                        finish()
                    }
                    "Kasir" -> {
                        startActivity(Intent(this, KasirActivity::class.java))
                        finish()
                    }
                    "Management" -> {
                        startActivity(Intent(this, ManagerActivity::class.java))
                        finish()
                    }

                }


            }
        }

    }

    private fun isValid() : Boolean {
        val username = binding.activityMainUsername.text.toString()
        val password = binding.activityMainPassword.text.toString()

        val getUser = userViewModel.getUserByUsername(username)

        if(username != getUser?.username){
            alert("Invalid Username")
            return false
        }

        if (password != getUser.password){
            alert("Invalid Password")
            return false
        }

        return true
    }

    private fun alert(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}