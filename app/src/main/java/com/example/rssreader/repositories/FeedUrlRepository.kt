package com.example.rssreader.repositories

import androidx.lifecycle.ViewModel
import com.example.rssreader.database.RssUrlDao
import com.example.rssreader.models.RssUrlItem
import kotlinx.coroutines.flow.Flow

class FeedUrlRepository(val rssUrlDao: RssUrlDao): ViewModel() {

    val urlList : Flow<List<RssUrlItem>> = rssUrlDao.getAllUrls()

    suspend fun insertUrl(url: RssUrlItem) {
        rssUrlDao.insertUrl(url)
    }

    suspend fun deleteUrl(url: RssUrlItem) {
        rssUrlDao.deleteUrl(url)
    }




}