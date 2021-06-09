package com.android.example.helloapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TopicEntity::class], version = 1)
abstract class TopicsDatabase: RoomDatabase() {
    abstract val topicDao: TopicDao
}

private lateinit var INSTANCE: TopicsDatabase

fun getDatabase(context: Context): TopicsDatabase {
    synchronized(TopicsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                TopicsDatabase::class.java,
                "topics").build()
        }
    }
    return INSTANCE
}