package com.example.rssreader.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.example.rssreader.models.RssUrlItem

class RssUrlViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var urlTextView: TextView? = null

    init {
        urlTextView = view.findViewById(R.id.urlTextView)
    }

    fun bind(rssUrlItem: RssUrlItem) {
        urlTextView?.text = rssUrlItem.url
    }

    companion object {
        fun create(parent: ViewGroup) : RssUrlViewHolder {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_url_item, parent, false)
            val rssUrlViewHolder = RssUrlViewHolder(view)
            return rssUrlViewHolder
        }
    }
}