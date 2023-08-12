package com.example.kasirukk.feature.admin.user.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.databinding.RvCardUserBinding
import com.example.kasirukk.feature.admin.user.viewmodel.AdminUserViewModel
import com.ukk.User

class AdminUserAdapter(private val listener: ClickListener, private val userViewModel: AdminUserViewModel): ListAdapter<User, AdminUserAdapter.ViewHolder>(
    PRODUCTS_COMPARATOR
) {
    companion object {
        private val PRODUCTS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(old: User, aNew: User): Boolean {

                if (old.username != aNew.username){
                    return false
                }

                if(old.name_user != aNew.name_user){
                    return false
                }

                if(old.role != aNew.role){
                    return false
                }

                return true
            }

            override fun areContentsTheSame(old: User, aNew: User): Boolean {
                return old == aNew
            }
        }
    }


    inner class ViewHolder(private val binding: RvCardUserBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(user: User?, listener: ClickListener) {
            binding.cardUserTvTitle.text = user?.name_user
            binding.cardUserTvDesc.text = user?.role
            binding.cardUserIvDelete.setOnClickListener {
                listener.onDeleteItemClicked(user!!)
            }
            binding.cardUserIvUpdate.setOnClickListener {
                listener.onUpdateItemClicked(user!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users, listener)
    }

    interface ClickListener {
        fun onUpdateItemClicked(item: User)
        fun onDeleteItemClicked(item: User)
    }

}