package com.example.kasirukk.feature.admin.menu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.AdminActivity
import com.example.kasirukk.databinding.FragmentAdminViewMenuBinding
import com.example.kasirukk.feature.admin.menu.adapter.AdminMenuMakananAdapter
import com.example.kasirukk.feature.admin.menu.adapter.AdminMenuMinumanAdapter
import com.example.kasirukk.feature.admin.menu.viewmodel.AdminMenuViewModel
import com.ukk.Menu
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
    private val viewModel by viewModels<AdminMenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminViewMenuBinding.inflate(inflater, container, false)

//        val setCurrentFragment = (activity as AdminActivity)

        initRvMakanan()
        initRvMinuman()

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


    override fun onUpdateMakananItemClicked(item: Menu) {
        val setCurrentFragment = (activity as AdminActivity)

//        setCurrentFragment.setCurrentFragment()
        onDestroyView()
    }

    override fun onDeleteMakananItemClicked(item: Menu) {
        viewModel.deleteMenu(item.id_menu)
    }

    override fun onUpdateMinumanItemClicked(item: Menu) {
        val setCurrentFragment = (activity as AdminActivity)

//      setCurrentFragment.setCurrentFragment()
        onDestroyView()
    }

    override fun onDeleteMinumanItemClicked(item: Menu) {
        viewModel.deleteMenu(item.id_menu)
    }
}