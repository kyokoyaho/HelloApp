package com.android.example.helloapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.android.example.helloapp.database.getDatabase
import com.android.example.helloapp.databinding.FragmentHomeBinding
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.domain.OutputTopicBean
import com.android.example.helloapp.domain.addNewTopics
import com.android.example.helloapp.repository.TopicsRepository
import timber.log.Timber

class HomeFragment : Fragment() {
    private lateinit var topicsRepository: TopicsRepository
    private lateinit var topics: LiveData<List<OutputTopicBean>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topicsRepository = TopicsRepository(getDatabase(requireContext()))
        topics = topicsRepository.topics

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)

        this.topics.observe(viewLifecycleOwner, Observer<List<OutputTopicBean>> { newTopics ->
            Timber.i("★★" + newTopics.toString())
            // topic_tableのデータを画面に表示する
            var outputText = StringBuilder()
            newTopics.forEach(){
                outputText.insert(0, "${it.toString()}\n\n")
            }
            binding.outputMemo.text = outputText.toString()
        })

        // 送信ボタン押下時の処理
        binding.submitButton.setOnClickListener { view ->
            // 入力データを保存する
            addNewTopics(topicsRepository, listOf(
                InputTopicBean(note = binding.memoEdittext.text.toString())
            ))
            // 入力フォームを空にする
            binding.memoEdittext.text.clear()
            // キーボードを非表示にする
            hideKeyboard(activity, view)
        }
        return binding.root
    }
}