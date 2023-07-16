package com.example.rssreader.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptyRecyclerViewDataObserver(rv: RecyclerView?, ev: View?) :
    RecyclerView.AdapterDataObserver() {

    private var emptyView = ev
    private var recyclerView: RecyclerView? = rv

    init {
        recyclerView = rv
        emptyView = ev
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        if (this.emptyView != null && this.recyclerView!!.adapter != null) {
            val emptyViewVisible = this.recyclerView!!.adapter!!.itemCount == 0
            this.emptyView!!.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            this.recyclerView!!.visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    override fun onChanged() {
        super.onChanged()
        checkIfEmpty()
    }
}