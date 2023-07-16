package com.example.rssreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FeedUrlRepository(val rssUrlDao: RssUrlDao): ViewModel() {

    val urlList : Flow<List<RssUrlItem>> = rssUrlDao.getAllUrls()

    suspend fun insertUrl(url: RssUrlItem) {
        rssUrlDao.insertUrl(url)
    }

    suspend fun deleteUrl(url: RssUrlItem) {
        rssUrlDao.deleteUrl(url)
    }




}