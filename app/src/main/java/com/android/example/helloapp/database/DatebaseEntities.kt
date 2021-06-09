package com.android.example.helloapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_table")
data class TopicEntity(
    @PrimaryKey(autoGenerate = true)
    var topicId: Long = 0L,
    var title: String = "",
    var note: String = "",
    var created: Long = System.currentTimeMillis(),
    var updated: Long = created
)