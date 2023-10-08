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
import com.example.kasirukk.databinding.FragmentAdminAddUserBinding
import com.example.kasirukk.feature.admin.user.viewmodel.AdminUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdminAddUserFragment : Fragment() {

    private var _binding: FragmentAdminAddUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AdminUserViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentAdminAddUserBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)
        val spinner = binding.fragmentAdminAddUserSpRole
        var role = ""

        binding.fragmentAdminAddUserIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminViewUserFragment())
            onDestroy()
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_job,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                role = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.fragmentAdminAddUserBtnSave.setOnClickListener{
            val name = binding.fragmentAdminAddUserEtName.text.toString()
            val username = binding.fragmentAdminAddUserEtUsername.text.toString()
            val password = binding.fragmentAdminAddUserEtPassword.text.toString()

            when{
                name.isEmpty() -> alert()
                name == "" -> alert()
                password.isEmpty() -> alert()
                password == "" -> alert()
                username.isEmpty() -> alert()
                username == "" -> alert()
                role == "Pilih" -> alert()

                else -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.insertUser(name_user = name, username = username, password = password, role = role)
                        setCurrentFragment.setCurrentFragment(AdminViewUserFragment())
                    }
                }
            }
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