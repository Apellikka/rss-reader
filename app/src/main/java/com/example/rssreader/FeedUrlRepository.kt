package com.example.rssreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FeedUrlRepository(val rssUrlDao: RssUrlDao): ViewModel() {

    private val url = RssUrlItem("https://www.schneier.com/feed/atom/")
    private val url2 = RssUrlItem("https://grahamcluley.com/feed")
    private val url3 = RssUrlItem("https://feeds.feedburner.com/TheHackersNews?format=xml")

    val urlList : Flow<List<RssUrlItem>> = rssUrlDao.getAllUrls()

    init {
        viewModelScope.launch {
            initialize()
        }
    }
    suspend fun initialize() {
        insertUrl(url)
        insertUrl(url2)
        insertUrl(url3)
    }


    suspend fun insertUrl(url: RssUrlItem) {
        rssUrlDao.insertUrl(url)
    }

    suspend fun deleteUrl(url: RssUrlItem) {
        rssUrlDao.deleteUrl(url)
    }




}