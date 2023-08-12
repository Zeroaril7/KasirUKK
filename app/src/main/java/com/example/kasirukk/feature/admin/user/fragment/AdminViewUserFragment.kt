package com.example.kasirukk.feature.admin.user.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kasirukk.AdminActivity
import com.example.kasirukk.databinding.FragmentAdminViewUserBinding
import com.example.kasirukk.feature.admin.user.adapter.AdminUserAdapter
import com.example.kasirukk.feature.admin.user.viewmodel.AdminUserViewModel
import com.ukk.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AdminViewUserFragment : Fragment(), AdminUserAdapter.ClickListener {

    private lateinit var userAdapter: AdminUserAdapter
    private var _binding: FragmentAdminViewUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AdminUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminViewUserBinding.inflate(inflater, container, false)

        val setCurrentFragment = (activity as AdminActivity)

        initRv()

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
        userAdapter = AdminUserAdapter(this, viewModel)
        val rvUser = binding.fragmentAdminViewUserRvUser
        rvUser.setHasFixedSize(true)
        rvUser.layoutManager = LinearLayoutManager(requireContext())
        rvUser.adapter = userAdapter

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.users.collect {
                    users -> userAdapter.submitList(users)
                    rvUser.adapter = userAdapter
            }
        }
    }

    override fun onUpdateItemClicked(item: User) {
        val setCurrentFragment = (activity as AdminActivity)
        setCurrentFragment.setCurrentFragment(AdminUpdateUserFragment(item))
        onDestroyView()
    }

    override fun onDeleteItemClicked(item: User) {
        Log.d("onDelete", "Is Clicked")
        Log.d("onDelete Id_User", item.id_user.toString())
        viewModel.deleteUser(item.id_user)
    }
}