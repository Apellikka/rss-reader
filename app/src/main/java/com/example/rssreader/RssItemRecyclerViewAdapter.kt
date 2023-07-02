package com.example.rssreader

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class RssItemRecyclerViewAdapter(private val context : Context) : ListAdapter<RssItem, RssItemViewHolder>(ItemsComparator()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RssItemViewHolder {
        return RssItemViewHolder.create(viewGroup)
    }

    override fun onViewRecycled(holder: RssItemViewHolder) {
        super.onViewRecycled(holder)
        collapseViewItem(holder)
    }

    override fun onBindViewHolder(holder: RssItemViewHolder, position: Int) {
        val current: RssItem = getItem(position)
        holder.bind(current)

        holder.itemView.setOnClickListener {
            toggleVisible(holder)
        }

        holder.readMore?.setOnClickListener {
           openWebView(holder)
        }
    }

    private fun collapseViewItem(holder : RssItemViewHolder) {
        holder.descriptionTextView?.visibility = View.GONE
        holder.readMore?.visibility = View.GONE
    }

    private fun openWebView(holder : RssItemViewHolder) {
            val extraUrl = holder.linkTextView?.text
            val intent = Intent(context, WebViewActivity::class.java)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("url" ,extraUrl)
            context.startActivity(intent)
    }

    private fun toggleVisible(holder : RssItemViewHolder) {
            if (holder.descriptionTextView?.visibility == View.VISIBLE) {
                holder.descriptionTextView?.visibility = View.GONE
                holder.readMore?.visibility = View.GONE
            } else {
                holder.descriptionTextView?.visibility = View.VISIBLE
                holder.readMore?.visibility = View.VISIBLE
            }
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