package com.example.rssreader

import android.util.Log
import java.time.LocalDateTime

class DateParser {

    companion object {

        fun parseValidDate(dateString: String?): LocalDateTime? {
            for (dateFormat in ValidDateFormats.validDateFormatsList())
                try {
                    return dateString?.let { LocalDateTime.parse(it, dateFormat) }
                } catch (e: Exception) {
                    Log.d("DateConverters", "ParseException!")
                    continue
                }
            return null
        }
    }

}