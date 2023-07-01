package com.example.rssreader

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [RssItem::class], version=1, exportSchema = false)
abstract class RssRoomDatabase : RoomDatabase()  {

    abstract fun rssDao() : RssDao

    companion object {

        @Volatile
        private var INSTANCE : RssRoomDatabase? = null

        fun getDatabase(context : Context, scope: CoroutineScope) : RssRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                RssRoomDatabase::class.java, "rss_database")
                    .addCallback(RssItemCallBack(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class RssItemCallBack(private val scope: CoroutineScope) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database -> scope.launch {
                clearDatabase(database.rssDao())
            } }
        }

        suspend fun clearDatabase(rssDao: RssDao) {
            rssDao.deleteAll()
        }
    }


}