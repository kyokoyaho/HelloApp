package com.android.example.helloapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.example.helloapp.database.TopicsDatabase
import com.android.example.helloapp.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopicsRepository(private val database: TopicsDatabase) {


    val topics: LiveData<List<OutputTopicBean>> =
        Transformations.map(database.topicDao.getTopics()) {
        it.asDomainModel()
    }

    // databaseにinsertする
    suspend fun insertTopics(topicBeans: List<InputTopicBean>) {
        withContext(Dispatchers.IO) {
            database.topicDao.insertAll(topicBeans.asDatabaseModel())
        }
    }
}