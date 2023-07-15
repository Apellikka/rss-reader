package com.example.rssreader

import kotlinx.coroutines.flow.Flow

class FeedUrlRepository(val rssUrlDao: RssUrlDao) {

    val urlList : Flow<List<RssUrlItem>> = rssUrlDao.getAllUrls()

    suspend fun insertUrl(url: RssUrlItem) {
        rssUrlDao.insertUrl(url)
    }




}