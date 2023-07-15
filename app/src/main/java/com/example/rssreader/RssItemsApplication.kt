package com.example.rssreader

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RssItemsApplication : Application()  {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy {RssRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {RssRepository(database.rssDao())}
    val urlRepository by lazy {FeedUrlRepository(database.rssUrlDao())}

}