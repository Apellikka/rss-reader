package com.example.rssreader.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rssreader.models.RssItem
import com.example.rssreader.models.RssUrlItem
import com.example.rssreader.utils.DateConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [RssItem::class, RssUrlItem::class], version=2, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class RssRoomDatabase : RoomDatabase()  {

    abstract fun rssDao(): RssItemDao
    abstract fun rssUrlDao(): RssUrlDao

    companion object {

        private val converters = DateConverters()

        @Volatile
        private var INSTANCE : RssRoomDatabase? = null

        fun getDatabase(context : Context, scope: CoroutineScope) : RssRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                RssRoomDatabase::class.java, "rss_database")
                    .addCallback(RssItemCallBack(scope))
                    .addTypeConverter(converters)
                    .fallbackToDestructiveMigration()
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

        suspend fun clearDatabase(rssItemDao: RssItemDao) {
            rssItemDao.deleteAll()
        }
    }
}