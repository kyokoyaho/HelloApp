package com.android.example.helloapp.addTopic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.example.helloapp.database.getDatabase
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.repository.TopicsRepository
import kotlinx.coroutines.launch

class AddTopicViewModel(application: Application): AndroidViewModel(application) {
    private var topicsRepository = TopicsRepository(getDatabase(application))

    // Home画面へのナビゲーション用 flag
    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    override fun onCleared() {
        super.onCleared()
    }

    /**
     * Topicの更新処理
     */
    fun addNewTopics(newTopics: List<InputTopicBean>){
        // 新しいtopicsを保存する
        viewModelScope.launch {
            topicsRepository.addNewTopics(newTopics)
        }
        _navigateToHome.value = true
    }
    fun onNavigateToHome(){
        _navigateToHome.value = false
    }
}