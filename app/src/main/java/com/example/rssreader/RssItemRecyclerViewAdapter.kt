package com.example.rssreader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RssItemRecyclerViewAdapter : ListAdapter<RssItem, RssItemViewHolder>(ItemsComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RssItemViewHolder {
        return RssItemViewHolder.create(viewGroup)
    }

    override fun onBindViewHolder(holder: RssItemViewHolder, position: Int) {
        val current: RssItem = getItem(position)
        holder.bind(current)
    }

    class ItemsComparator : DiffUtil.ItemCallback<RssItem>() {
        override fun areItemsTheSame(oldItem: RssItem, newItem: RssItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RssItem, newItem: RssItem): Boolean {
            return oldItem.title == newItem.title
        }
    }

}