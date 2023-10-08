package com.example.kasirukk.feature.admin.meja.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.databinding.FragmentAdminUpdateMejaBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.ukk.data.Meja
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminUpdateMejaFragment(private val meja: Meja) : Fragment() {

    private var _binding: FragmentAdminUpdateMejaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AdminMejaViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUpdateMejaBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        binding.fragmentAdminUpdateMejaIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminViewMejaFragment())
            onDestroy()
        }

        val number = binding.fragmentAdminUpdateMejaEtNumber

        number.setText(meja.number.toString())

        binding.fragmentAdminUpdateMejaBtnSave.setOnClickListener {
            val newNumber = number.text.toString()
            val nomor = newNumber.toLong()

            when{
                newNumber.isEmpty() -> alert()
                newNumber == "" -> alert()
                nomor <= 0 -> alert()

                else -> {
                    viewModel.updateMeja(id = meja.id_meja, number = nomor, isUsed = false)
                    setCurrentFragment.setCurrentFragment(AdminViewMejaFragment())
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