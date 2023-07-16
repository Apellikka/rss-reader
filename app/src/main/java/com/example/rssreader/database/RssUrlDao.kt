package com.example.rssreader.database

import androidx.room.*
import com.example.rssreader.models.RssUrlItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RssUrlDao {

    @Query("SELECT * FROM rssUrl_table")
    fun getAllUrls(): Flow<List<RssUrlItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUrl(url: RssUrlItem)

    @Delete
    suspend fun deleteUrl(url: RssUrlItem)
}