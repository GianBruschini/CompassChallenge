package com.conexa.conexachallenge.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.databinding.NewsItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    private var context: Context? = null
    private var listOfNews = ArrayList<NewsResponse>()
    private var mListener: OnItemClickListener? = null


    interface OnItemClickListener {
        fun onitemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        this.context = parent.context
        return MyViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val url = listOfNews[position].thumbnail
        context?.let { Glide.with(it).load(url).into(holder.binding.newsImage) }
        holder.binding.newsTitle.text = listOfNews[position].title

    }


    override fun getItemCount(): Int {
        return listOfNews.size
    }

    fun setList(listOfNews: ArrayList<NewsResponse>) {
        this.listOfNews.clear()
        this.listOfNews = listOfNews
    }

    inner class MyViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cardNews.setOnClickListener {
                if (mListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        mListener!!.onitemClick(position)
                    }
                }
            }

        }
    }
}