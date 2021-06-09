package com.android.example.helloapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.example.helloapp.database.getDatabase
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.domain.OutputTopicBean
import com.android.example.helloapp.repository.TopicsRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private lateinit var topicsRepository: TopicsRepository
    lateinit var topics: LiveData<List<OutputTopicBean>>

    init {
        topicsRepository = TopicsRepository(getDatabase(application))
        topics = topicsRepository.topics
        Timber.i("HomeViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("HomeViewModel destroyed!")
    }

    /**
     * 送信ボタン押下時の処理
     */
    fun addNewTopics(newTopics: List<InputTopicBean>){
        // 新しいtopicsを保存する
        viewModelScope.launch {
            topicsRepository.addNewTopics(newTopics)
        }
    }
}