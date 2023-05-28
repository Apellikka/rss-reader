package com.example.rssreader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RssItemRecyclerViewAdapter(private val dataSet: Array<RssItem>) :
    RecyclerView.Adapter<RssItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RssItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_item, viewGroup, false)
        return RssItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RssItemViewHolder, position: Int) {
        val current: RssItem = dataSet[position]
        holder.bind(current)
    }

}