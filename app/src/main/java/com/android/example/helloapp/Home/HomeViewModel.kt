package com.android.example.helloapp.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    // AddTopic画面へのナビゲーション用 flag
    private val _navigateToAddTopic = MutableLiveData<Boolean>()
    val navigateToAddTopic: LiveData<Boolean>
        get() = _navigateToAddTopic

    init {
        topicsRepository = TopicsRepository(getDatabase(application))
        topics = topicsRepository.topics
        Timber.i("HomeViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("HomeViewModel destroyed!")
    }

    // FloatingActionButtonクリック時の処理
    fun onFabClicked(){
        _navigateToAddTopic.value = true
    }
    fun onNavigateToAddTopic(){
        _navigateToAddTopic.value = false
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

    fun removeTopic(topicId: Long){
        viewModelScope.launch {
            topicsRepository.removeTopic(topicId)
        }
    }
}