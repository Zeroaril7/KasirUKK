package com.example.kasirukk.feature.admin.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.feature.admin.AdminActivity
import com.example.kasirukk.feature.login.USER
import com.example.kasirukk.databinding.FragmentAdminViewMenuBinding
import com.example.kasirukk.feature.admin.menu.adapter.AdminMenuMakananAdapter
import com.example.kasirukk.feature.admin.menu.adapter.AdminMenuMinumanAdapter
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.ukk.data.Menu
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdminViewMenuFragment : Fragment(), AdminMenuMakananAdapter.ClickListener, AdminMenuMinumanAdapter.ClickListener {

    private lateinit var menuMakananAdapter: AdminMenuMakananAdapter
    private lateinit var menuMinumanAdapter: AdminMenuMinumanAdapter
    private var _binding: FragmentAdminViewMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<AdminMenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminViewMenuBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        binding.fragmentAdminViewUserTvUser.text = "Welcome, $USER"

        binding.logout.setOnClickListener {
            setCurrentFragment.logout()
        }

        initRvMakanan()
        initRvMinuman()

        binding.fragmentAdminViewMenuFabAdd.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminAddMenuFragment())
            onDestroyView()
        }

        return binding.root
    }

    private fun initRvMakanan() {
        menuMakananAdapter = AdminMenuMakananAdapter(this, viewModel)
        val rvMenuMakanan = binding.fragmentAdminViewMenuRvMakanan
        rvMenuMakanan.setHasFixedSize(true)
        rvMenuMakanan.layoutManager = LinearLayoutManager(requireContext())
        rvMenuMakanan.adapter = menuMakananAdapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.menuMakanan.collect {
                data -> menuMakananAdapter.submitList(data)
                rvMenuMakanan.adapter = menuMakananAdapter
            }
        }
    }

    private fun initRvMinuman(){
        menuMinumanAdapter = AdminMenuMinumanAdapter(this, viewModel)

        val rvMenuMinuman = binding.fragmentAdminViewMenuRvMinuman
        rvMenuMinuman.setHasFixedSize(true)
        rvMenuMinuman.layoutManager = LinearLayoutManager(requireContext())
        rvMenuMinuman.adapter = menuMinumanAdapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.menuMinuman.collect {
                    data -> menuMinumanAdapter.submitList(data)
                rvMenuMinuman.adapter = menuMinumanAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onUpdateMakananItemClicked(item: Menu) {
        val setCurrentFragment = (activity as AdminActivity)
        setCurrentFragment.setCurrentFragment(AdminUpdateMenuFragment(item))
        onDestroyView()
    }

    override fun onUpdateMinumanItemClicked(item: Menu) {
        val setCurrentFragment = (activity as AdminActivity)
        setCurrentFragment.setCurrentFragment(AdminUpdateMenuFragment(item))
        onDestroyView()
    }
}