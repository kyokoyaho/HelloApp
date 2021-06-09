package com.android.example.helloapp.domain

import com.android.example.helloapp.database.TopicEntity

data class OutputTopicBean(
    var topicId: Long,
    var title: String,
    var note: String,
    var created: Long,
    var updated: Long
)

/**
 * データベースのEntityからOutputTopicBeanに変換する
 */
fun List<TopicEntity>.asDomainModel(): List<OutputTopicBean> {
    return map {
        OutputTopicBean(
            topicId = it.topicId,
            title = it.title,
            note = it.note,
            created = it.created,
            updated = it.updated)
    }
}

data class InputTopicBean(
    var note: String
)

/**
 * InputTopicBeanからデータベースのEntityに変換する
 */
fun List<InputTopicBean>.asDatabaseModel(): List<TopicEntity> {
    return map {
        TopicEntity(
            note = it.note
        )
    }
}