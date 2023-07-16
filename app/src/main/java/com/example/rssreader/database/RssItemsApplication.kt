package com.example.rssreader.database

import android.app.Application
import com.example.rssreader.repositories.FeedUrlRepository
import com.example.rssreader.repositories.RssRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RssItemsApplication : Application()  {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RssRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { RssRepository(database.rssDao(), database.rssUrlDao()) }
    val urlRepository by lazy { FeedUrlRepository(database.rssUrlDao()) }

}