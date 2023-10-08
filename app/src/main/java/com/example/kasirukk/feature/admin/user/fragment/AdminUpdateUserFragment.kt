package com.example.kasirukk.feature.admin.user.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.R
import com.example.kasirukk.databinding.FragmentAdminUpdateUserBinding
import com.example.kasirukk.feature.admin.user.viewmodel.AdminUserViewModel
import com.ukk.data.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminUpdateUserFragment(private val user : User) : Fragment() {

    private var _binding: FragmentAdminUpdateUserBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<AdminUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUpdateUserBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        val name = binding.fragmentAdminUpdateUserEtName
        val username = binding.fragmentAdminUpdateUserEtUsername
        val password = binding.fragmentAdminUpdateUserEtPassword
        val spinner = binding.fragmentAdminUpdateUserSpRole
        var role = user.role

        name.setText(user.name_user)
        username.setText(user.username)
        password.setText(user.password)

        val idJob : Int = when (role) {
            "Admin" -> {
                1
            }
            "Kasir" -> {
                2
            }
            "Management" -> {
                3
            }
            else -> {
                4
            }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_job,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(idJob)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinner.setSelection(p2)
                role = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.fragmentAdminUpdateUserBtnSave.setOnClickListener {
            val newName = name.text.toString()
            val newUsername = username.text.toString()
            val newPassword = password.text.toString()


            when{

                newName.isEmpty() -> alert()
                newName == "" -> alert()
                newUsername.isEmpty() -> alert()
                newUsername == "" -> alert()
                newPassword == "" -> alert()
                role == "Pilih" -> alert()

                else -> {
                    viewModel.updateUser(id = user.id_user, name_user = newName, username = newUsername, password = newPassword, role = role)
                    setCurrentFragment.setCurrentFragment(AdminViewUserFragment())
                    onDestroy()
                }
            }
        }

        binding.fragmentAdminUpdateUserIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminViewUserFragment())
            onDestroy()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun alert(){
        Toast.makeText(requireContext(), "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}