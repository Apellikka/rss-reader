package com.example.rssreader

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rssUrl_table")
data class RssUrlItem(
    @PrimaryKey
    val url: String
)
