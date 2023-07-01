package com.example.rssreader

import android.util.Log
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "rssItem_table")
data class RssItem(
    @PrimaryKey
    val title: String,
    val guid: String?,
    val description: String?,
    val pubDate: String?) {

    override fun toString() : String {
        return "$title $guid $description $pubDate"
    }
}