package com.example.rssreader.models

import androidx.lifecycle.*
import com.example.rssreader.repositories.RssRepository

class RssItemViewModel(var rssRepository: RssRepository) : ViewModel()  {

    val allItems: LiveData<List<RssItem>> = rssRepository.rssList.asLiveData()

    fun getFeed() {
        rssRepository.getFeed()
    }

    class RssViewModelFactory(private val repository: RssRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RssItemViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RssItemViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}