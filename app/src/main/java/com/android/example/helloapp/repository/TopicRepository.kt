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
    private suspend fun insertTopics(topicBeans: List<InputTopicBean>) {
        withContext(Dispatchers.IO) {
            database.topicDao.insertAll(topicBeans.asDatabaseModel())
        }
    }
    // databaseから指定のtopicをdeleteする
    private suspend fun deleteTopic(topicId: Long){
        withContext(Dispatchers.IO) {
            database.topicDao.deleteTopic(topicId)
        }
    }

    /**
     * 新しいtopicsを保存する
     */
    suspend fun addNewTopics(newTopics: List<InputTopicBean>){
        // databaseに保存する
        insertTopics(newTopics)
    }

    /**
     * 指定のTopicを削除する
     */
    suspend fun removeTopic(topicId: Long){
        deleteTopic(topicId)
    }
}