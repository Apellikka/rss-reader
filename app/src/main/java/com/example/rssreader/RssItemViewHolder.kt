package com.example.rssreader

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RssItemViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    var titleTextView: TextView? = null
    var linkTextView: TextView? = null
    var descriptionTextView: TextView? = null
    var pubDateTextView: TextView? = null

    init {
        titleTextView = view.findViewById(R.id.title)
        linkTextView = view.findViewById(R.id.link)
        descriptionTextView = view.findViewById(R.id.description)
        pubDateTextView = view.findViewById(R.id.pubDate)
    }

    fun bind(item: RssItem) {
        titleTextView?.text = item.title
        linkTextView?.text = item.link
        descriptionTextView?.text = item.description
        pubDateTextView?.text = item.pubDate
    }
}