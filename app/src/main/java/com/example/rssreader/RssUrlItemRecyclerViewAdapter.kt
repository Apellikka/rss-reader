package com.example.rssreader

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class RssUrlItemRecyclerViewAdapter() : ListAdapter<RssUrlItem, RssUrlViewHolder>(UrlComparator()) {

    private var urlItem : RssUrlItem? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssUrlViewHolder {
        return RssUrlViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RssUrlViewHolder, position: Int) {
        val current: RssUrlItem = getItem(position)
        holder.bind(current)

        holder.itemView.setOnClickListener {
            urlItem = current
        }
    }

    fun getUrl(): RssUrlItem? {
        return urlItem
    }

    class UrlComparator : DiffUtil.ItemCallback<RssUrlItem>() {
        override fun areItemsTheSame(oldItem: RssUrlItem, newItem: RssUrlItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RssUrlItem, newItem: RssUrlItem): Boolean {
            return oldItem.url == newItem.url
        }
    }
}