package com.example.rssreader

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RssUrlDao {

    @Query("SELECT * FROM rssUrl_table")
    fun getAllUrls(): Flow<List<RssUrlItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(url: RssUrlItem)
}