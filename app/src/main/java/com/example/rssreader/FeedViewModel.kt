package com.example.rssreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData

class FeedViewModel(var rssUrlRepository: FeedUrlRepository) : ViewModel() {

    val allUrls: LiveData<List<RssUrlItem>> = rssUrlRepository.urlList.asLiveData()


    class FeedViewModelFactory(private val rssUrlRepository: FeedUrlRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FeedViewModel(rssUrlRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}