package com.example.rssreader

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class FeedViewModel(var rssUrlRepository: FeedUrlRepository) : ViewModel() {

    val allUrls: LiveData<List<RssUrlItem>> = rssUrlRepository.urlList.asLiveData()

    fun insertUrl(url: RssUrlItem) {
        viewModelScope.launch {
            rssUrlRepository.insertUrl(url)
        }
    }

    fun deleteUrl(url: RssUrlItem) {
        viewModelScope.launch {
            rssUrlRepository.deleteUrl(url)
        }
    }


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