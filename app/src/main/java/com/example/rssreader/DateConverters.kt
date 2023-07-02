package com.example.rssreader

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.rssreader.DateParser.Companion.parseValidDate
import java.time.LocalDateTime

@ProvidedTypeConverter
class DateConverters {

    @TypeConverter
    fun fromTimestamp(dateString: String?): LocalDateTime? {
        return parseValidDate(dateString)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }

}