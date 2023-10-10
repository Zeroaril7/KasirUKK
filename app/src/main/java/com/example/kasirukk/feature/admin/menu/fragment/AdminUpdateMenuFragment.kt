package com.example.kasirukk.feature.admin.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.databinding.FragmentAdminUpdateMenuBinding
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.ukk.data.Menu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminUpdateMenuFragment(private val menu: Menu) : Fragment() {

    private var _binding: FragmentAdminUpdateMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AdminMenuViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUpdateMenuBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        binding.fragmentAdminUpdateMenuIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminViewMenuFragment())
            onDestroy()
        }

        val name = binding.fragmentAdminUpdateMenuEtName
        val description = binding.fragmentAdminUpdateMenuEtDesc
        val price = binding.fragmentAdminUpdateMenuEtHarga

        name.setText(menu.name_menu)
        description.setText(menu.description)
        price.setText(menu.price.toString())

        binding.fragmentAdminUpdateMenuBtnSave.setOnClickListener{
            val newName = name.text.toString()
            val newDescription = description.text.toString()
            val newPrice = price.text.toString()

            var harga : Long = 0

            if (newPrice.isNotEmpty()){
                harga = newPrice.toLong()
            }

            when{
                newName.isEmpty() -> alert()
                newName == "" -> alert()
                newDescription.isEmpty() -> alert()
                newDescription == "" -> alert()
                newPrice.isEmpty() -> alert()
                newPrice == ""-> alert()
                harga <= 0 -> alert()

                else -> {
                    viewModel.updateMenu(id = menu.id_menu, name_menu = newName, description = newDescription, price = harga)
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