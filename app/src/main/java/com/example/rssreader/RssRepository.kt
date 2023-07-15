package com.example.rssreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.DateParser.Companion.parseValidDate
import com.prof.rssparser.Parser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class RssRepository(val rssItemDao: RssItemDao, val rssUrlDao: RssUrlDao) : ViewModel() {

    private var list: List<String> = emptyList()
    private val parser = Parser.Builder().build()

    val rssList: Flow<List<RssItem>> = rssItemDao.getAllItems()

    val allUrls: Flow<List<RssUrlItem>> = rssUrlDao.getAllUrls()

    suspend fun insert(item: RssItem) {
        rssItemDao.insert(item)
    }

    private suspend fun clearDatabase() {
        rssItemDao.deleteAll()
    }

    init {
        GlobalScope.launch {
            FeedUrlRepository(rssUrlDao).initialize()
            allUrls.collect { list = it.map(RssUrlItem::url)}
        }
        getFeed()
    }

    fun getFeed() {
        viewModelScope.launch {
            clearDatabase()
            for (url in list) {
                try {
                    val channel = parser.getChannel(url)
                    for (article in channel.articles) {
                        val pubDate: LocalDateTime? = parseValidDate(article.pubDate)
                        val item =
                            RssItem(
                                article.title.toString(),
                                article.guid,
                                article.description,
                                pubDate
                            )
                        insert(item)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}