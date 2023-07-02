package com.example.rssreader

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssreader.DateParser.Companion.parseValidDate
import com.prof.rssparser.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class RssRepository(val rssDao : RssDao) : ViewModel() {

    private val url = "https://www.schneier.com/feed/atom/"
    private val url2 = "https://grahamcluley.com/feed"
    private val url3 = "https://feeds.feedburner.com/TheHackersNews?format=xml"

    private var list: ArrayList<String> = ArrayList()

    val parser = Parser.Builder().build()

    val rssList : Flow<List<RssItem>> = rssDao.getAllItems()
    suspend fun insert(item : RssItem) {
        rssDao.insert(item)
    }

    suspend fun clearDatabase() {
        rssDao.deleteAll()
    }

    init {
        list.add(url)
        list.add(url2)
        list.add(url3)
        getFeed()
    }

    private fun getFeed() {
        viewModelScope.launch {
            clearDatabase()
            for (urli in list) {
                try {
                    val channel = parser.getChannel(urli)
                    for (article in channel.articles) {
                        val pubDate : LocalDateTime? = parseValidDate(article.pubDate)
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