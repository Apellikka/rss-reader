package com.example.rssreader

import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RssItemViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    var titleTextView: TextView? = null
    var linkTextView: TextView? = null
    var descriptionTextView: TextView? = null
    var pubDateTextView: TextView? = null
    var readMore: Button? = null

    init {
        titleTextView = view.findViewById(R.id.title)
        linkTextView = view.findViewById(R.id.link)
        descriptionTextView = view.findViewById(R.id.description)
        pubDateTextView = view.findViewById(R.id.pubDate)
        readMore = view.findViewById(R.id.readMoreButton)
    }

    fun bind(item: RssItem) {
        titleTextView?.text = item.title
        linkTextView?.text = item.guid
        descriptionTextView?.text = formatDescriptionText(item.description)
        pubDateTextView?.text = item.dateFormatted
        readMore
    }

    private fun formatDescriptionText(description: String?) : String {
        return Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }

    companion object {
        fun create(parent: ViewGroup) : RssItemViewHolder {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
            val rssItemViewHolder = RssItemViewHolder(view)
            rssItemViewHolder.descriptionTextView?.movementMethod = LinkMovementMethod.getInstance()
            return rssItemViewHolder
        }
    }
}