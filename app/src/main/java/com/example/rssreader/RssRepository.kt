package com.example.rssreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prof.rssparser.Parser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RssRepository(val rssDao : RssDao) : ViewModel() {

    private val url = "https://www.schneier.com/feed/atom/"
    private val url2 = "https://grahamcluley.com/feed"
    private val url3 = "https://feeds.feedburner.com/TheHackersNews?format=xml"

    val parser = Parser.Builder().build()

    val rssList : Flow<List<RssItem>> = rssDao.getAllItems()
    suspend fun insert(item : RssItem) {
        rssDao.insert(item)
    }

    private suspend fun clearDatabase() {
        rssDao.deleteAll()
    }

    init {
        getFeed()
    }

    private fun getFeed() {
        viewModelScope.launch {
            // Clear it on app exit, rather than on getting the feed?
            clearDatabase()
            try {
                val channel = parser.getChannel(url2)
                for (article in channel.articles) {
                    var item  =
                        RssItem(article.title.toString(), article.guid, article.description, article.pubDate)
                    insert(item)
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}