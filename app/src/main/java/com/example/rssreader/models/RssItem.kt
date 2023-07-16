package com.example.rssreader.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "rssItem_table")
data class RssItem(
    @PrimaryKey
    val title: String,
    val guid: String?,
    val description: String?,
    val pubDate: LocalDateTime?) {

    val dateFormatted : String?
        get() = pubDate?.format(DateTimeFormatter.ofPattern("dd.MM HH:mm"))

    override fun toString() : String {
        return "$title $guid $description $pubDate"
    }


}