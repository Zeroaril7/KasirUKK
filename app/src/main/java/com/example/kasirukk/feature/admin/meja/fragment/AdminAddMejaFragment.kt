package com.example.kasirukk.feature.admin.meja.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.databinding.FragmentAdminAddMejaBinding
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminAddMejaFragment : Fragment() {

    private var _binding: FragmentAdminAddMejaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AdminMejaViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminAddMejaBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        binding.fragmentAdminAddMejaIbBack.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminViewMejaFragment())
            onDestroy()
        }
        binding.fragmentAdminAddMejaBtnSave.setOnClickListener {
            val number = binding.fragmentAdminAddMejaEtNumber.text.toString()
            var nomor : Long = 0

            if (number.isNotEmpty()){
                nomor = number.toLong()
            }
            when{
                number.isEmpty() -> alert()
                number == "" -> alert()
                nomor <= 0 -> alert()

                else -> {
                    viewModel.insertMeja(nomor)
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