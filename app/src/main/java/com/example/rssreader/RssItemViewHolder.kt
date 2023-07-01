package com.example.rssreader

import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    init {
        titleTextView = view.findViewById(R.id.title)
        linkTextView = view.findViewById(R.id.link)
        descriptionTextView = view.findViewById(R.id.description)
        pubDateTextView = view.findViewById(R.id.pubDate)
    }

    fun bind(item: RssItem) {
        titleTextView?.text = item.title
        linkTextView?.text = formatGuid(item.guid)
        descriptionTextView?.text = formatDescriptionText(item.description)
        pubDateTextView?.text = formatPubDate(item.pubDate)
    }

    private fun formatGuid(guid: String?) : String {
        return "Read more at: $guid"
    }
    private fun formatPubDate(pubDate: String?) : String {
        for (dateFormat in ValidDateFormats.validDateFormatsList()) {
            try {
                val parsedDate = LocalDateTime.parse(pubDate, dateFormat)
                return parsedDate.format(DateTimeFormatter.ofPattern("dd.MM HH:mm"))
            } catch (e: Exception) {
                Log.d("RssItemViewHolder", "ParseException!")
                continue
            }
        }
        return pubDate.toString()
    }

    private fun formatDescriptionText(description: String?) : Spanned {
        return Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
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