package com.example.kasirukk.feature.admin.menu.fragment

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
import com.example.kasirukk.databinding.FragmentAdminAddMenuBinding
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminAddMenuFragment : Fragment() {

    private var _binding: FragmentAdminAddMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AdminMenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminAddMenuBinding.inflate(inflater, container, false)
        val setCurrentFragment = (activity as AdminActivity)

        val spinner = binding.fragmentAdminAddUserSpType
        var type = ""

        binding.fragmentAdminAddMenuIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminViewMenuFragment())
            onDestroy()
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_menu,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                type = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.fragmentAdminAddMenuBtnSave.setOnClickListener {
            val name = binding.fragmentAdminAddMenuEtName.text.toString()
            val description = binding.fragmentAdminAddMenuEtDesc.text.toString()
            val price = binding.fragmentAdminAddMenuEtHarga.text.toString()
            val harga = price.toLong()

            when{

                name.isEmpty() -> alert()
                name == "" -> alert()
                description.isEmpty() -> alert()
                description == "" -> alert()
                price.isEmpty() -> alert()
                price == "" -> alert()
                harga <= 0.0 -> alert()
                type == "Pilih" -> alert()


                else -> {

                    viewModel.insertMenu(name_menu = name, description = description, price = harga, type = type)
                    setCurrentFragment.setCurrentFragment(AdminViewMenuFragment())
                    onDestroy()
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