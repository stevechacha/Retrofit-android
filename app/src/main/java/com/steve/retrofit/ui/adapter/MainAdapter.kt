package com.steve.retrofit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.steve.retrofit.databinding.PersonItemBinding
import com.steve.retrofit.model.User
import kotlinx.android.synthetic.main.person_item.view.*


class MainAdapter(private val users: ArrayList<User>) :RecyclerView.Adapter<MainAdapter.DataViewHolder>(){
    class DataViewHolder(private val itemView:PersonItemBinding) :RecyclerView.ViewHolder(itemView.root){
        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int =users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])

    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}