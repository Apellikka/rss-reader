package com.example.rssreader

import java.time.format.DateTimeFormatter

class ValidDateFormats {
    companion object {
        fun validDateFormatsList() : ArrayList<DateTimeFormatter> {
            val validFormats: ArrayList<DateTimeFormatter> = ArrayList()
            val isoDateTime: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
            val atomDateTime: DateTimeFormatter = DateTimeFormatter.RFC_1123_DATE_TIME
            validFormats.add(isoDateTime)
            validFormats.add(atomDateTime)
            return validFormats
        }
    }
}