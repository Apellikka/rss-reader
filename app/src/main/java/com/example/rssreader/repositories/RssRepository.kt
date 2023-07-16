package com.example.rssreader.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.models.RssItem
import com.example.rssreader.database.RssItemDao
import com.example.rssreader.database.RssUrlDao
import com.example.rssreader.models.RssUrlItem
import com.example.rssreader.utils.DateParser.Companion.parseValidDate
import com.prof.rssparser.Parser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

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