package com.example.kasirukk.feature.admin.ui.user.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kasirukk.AdminActivity
import com.example.kasirukk.databinding.FragmentAdminViewUserBinding
import com.example.kasirukk.feature.admin.ui.user.adapter.AdminUserAdapter
import com.example.kasirukk.feature.admin.ui.user.viewmodel.AdminUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdminViewUserFragment : Fragment() {

    private lateinit var userAdapter: AdminUserAdapter
    private var _binding: FragmentAdminViewUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel : AdminUserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminViewUserBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        userAdapter = AdminUserAdapter()
        val rvUser = binding.fragmentAdminViewUserRvUser
        rvUser.setHasFixedSize(true)
        rvUser.adapter = userAdapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.users.collect {
                    users -> userAdapter.submitList(users)
                rvUser.adapter = userAdapter
            }
        }

        binding.fragmentAdminViewUserFabAdd.setOnClickListener {
            setCurrentFragment.setCurrentFragment(AdminAddUserFragment())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRv() {

    }
}