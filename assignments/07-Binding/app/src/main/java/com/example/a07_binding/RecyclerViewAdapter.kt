package com.example.a07_binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.a07_binding.databinding.NewsRecyclerviewLayoutBinding

class RecyclerViewAdapter(
    private val news: ArrayList<NewsModel>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NewsRecyclerviewLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_recyclerview_layout,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(private val binding: NewsRecyclerviewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            binding.news = news[adapterPosition]
        }
    }
}