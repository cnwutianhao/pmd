package com.tyhoo.android.trad.pmd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.trad.pmd.data.response.News
import com.tyhoo.android.trad.pmd.databinding.ListItemNewsBinding

class NewsAdapter : ListAdapter<News, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            ListItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = getItem(position)
        (holder as NewsViewHolder).bind(news)
    }

    class NewsViewHolder(
        private val binding: ListItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.apply {
                news = item
                executePendingBindings()
            }
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.newsId == newItem.newsId
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}