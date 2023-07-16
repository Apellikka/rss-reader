package com.example.rssreader.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rssreader.viewholders.RssUrlViewHolder
import com.example.rssreader.models.RssUrlItem

typealias OnUrlClick = (url: RssUrlItem) -> Unit
class RssUrlItemRecyclerViewAdapter : ListAdapter<RssUrlItem, RssUrlViewHolder>(UrlComparator()) {

    var onUrlClick: OnUrlClick? = null
    var urlItem: RssUrlItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssUrlViewHolder {
        return RssUrlViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RssUrlViewHolder, position: Int) {
        val current: RssUrlItem = getItem(position)
        holder.bind(current)

        holder.itemView.setOnClickListener {
            urlItem = current
            onUrlClick?.invoke(current)
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