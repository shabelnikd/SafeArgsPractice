package com.shabelnikd.safeargspractice.screens.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shabelnikd.safeargspractice.databinding.ItemUserBinding
import com.shabelnikd.safeargspractice.screens.database.entities.UserTuple
import com.shabelnikd.safeargspractice.screens.models.User

class UserAdapter(
    private val users: ArrayList<UserTuple>,
    private val onDeleteClick: (position: Int, id: Long) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        with(holder) {

            binding.tvName.text = "${user.name} / id = ${user.id}"
            binding.tvEmail.text = user.email
            binding.tvPassword.text = user.password.toString()

            binding.btnDelete.setOnClickListener{
                onDeleteClick(position, user.id)
                notifyItemRemoved(position)
            }
        }

    }

    override fun getItemCount(): Int = users.size

}