package com.conexa.conexachallenge.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.databinding.NewsItemBinding
import com.conexa.conexachallenge.databinding.UserItemBinding

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    private var context: Context? = null
    private var listOfUsers = ArrayList<UserResponse>()
    private var mListener: OnItemClickListener? = null


    interface OnItemClickListener {
        fun onUserItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        this.context = parent.context
        return MyViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.usersTitle.text = listOfUsers[position].email
    }


    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    fun setList(listOfUsers: ArrayList<UserResponse>) {
        this.listOfUsers.clear()
        this.listOfUsers = listOfUsers
    }

    inner class MyViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.usersOpenMap.setOnClickListener {
                if (mListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        mListener!!.onUserItemClick(position)
                    }
                }
            }

        }
    }
}