package com.android.example.helloapp.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TopicDao {
    @Query("select * from topic_table")
    fun getTopics(): LiveData<List<TopicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( videos: List<TopicEntity>)

    @Query("DELETE FROM topic_table WHERE topicId = :topicId")
    fun deleteTopic(topicId: Long)
}