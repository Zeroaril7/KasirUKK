package com.example.kasirukk.feature.admin.ui.user.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kasirukk.MainActivity
import com.example.kasirukk.databinding.RvCardUserBinding
import com.example.kasirukk.feature.admin.ui.user.viewmodel.AdminUserViewModel
import com.ukk.User
import kotlin.math.log

class AdminUserAdapter(): ListAdapter<User,AdminUserAdapter.ViewHolder>(
    PRODUCTS_COMPARATOR) {

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
        fun bind(user: User?) {
            binding.cardUserTvTitle.text = user?.name_user
            binding.cardUserTvDesc.text = user?.role
            println(user?.name_user + "in Adapter ViewHolder")
//            binding.cardUserIvDelete.setOnClickListener {
//                if (user != null) {
//                    userViewModel.deleteUser(user.id_user)
//                }
//            }
        }

//        fun onClick(context: Context, user: User){
//            binding.cardUserIvUpdate.setOnClickListener {
//                val intent = Intent(context, MainActivity::class.java)
//                intent.putExtra("id", user.id_user)
//                intent.putExtra("name", user.name_user)
//                intent.putExtra("username", user.username)
//                intent.putExtra("password", user.password)
//                intent.putExtra("job", user.role)
//                context.startActivity(intent)
//            }
//        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = getItem(position)
        println(users)
        holder.bind(users)
//        holder.onClick(context = context, users)
    }

}