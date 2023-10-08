package com.example.kasirukk.feature.admin.meja.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.feature.login.USER
import com.example.kasirukk.databinding.FragmentAdminViewMejaBinding
import com.example.kasirukk.feature.admin.meja.adapter.AdminMejaAdapter
import com.example.kasirukk.feature.admin.meja.viewmodel.AdminMejaViewModel
import com.ukk.data.Meja
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdminViewMejaFragment : Fragment(), AdminMejaAdapter.ClickListener {

    private var _binding: FragmentAdminViewMejaBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<AdminMejaViewModel>()
    private lateinit var mejaAdapter: AdminMejaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminViewMejaBinding.inflate(inflater, container, false)
        val setCurrentFragment = (activity as AdminActivity)

        binding.fragmentAdminViewMejaTvUser.text = "Welcome, $USER"

        binding.logout.setOnClickListener {
            setCurrentFragment.logout()
        }

        initRv()

        binding.fragmentAdminViewMejaFabAdd.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminAddMejaFragment())
            onDestroyView()
        }

        return binding.root
    }

    private fun initRv(){
        mejaAdapter = AdminMejaAdapter(this, viewModel)

        val rvMeja = binding.fragmentAdminViewMejaRvTable
        rvMeja.setHasFixedSize(true)
        rvMeja.layoutManager = LinearLayoutManager(requireContext())
        rvMeja.adapter = mejaAdapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.mejas.collect {
                data -> mejaAdapter.submitList(data)
                rvMeja.adapter = mejaAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onUpdateItemClicked(item: Meja) {
        val setCurrentFragment = (activity as AdminActivity)
        setCurrentFragment.setCurrentFragment(AdminUpdateMejaFragment(item))
        onDestroyView()
    }
}