package com.example.rssreader

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Query("SELECT * FROM rssItem_table ORDER BY pubDate DESC")
    fun getAllItems(): Flow<List<RssItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : RssItem)

    @Query("DELETE FROM rssItem_table")
    suspend fun deleteAll()
}