package com.android.example.helloapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.helloapp.databinding.FragmentHomeBinding
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.domain.OutputTopicBean
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)

        // ViewModelを作成
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // RecyclerViewのアダプターを設定
        val adapter = TopicAdapter()
        binding.topicList.adapter = adapter

        // ViewModel内の変数を監視する
        viewModel.topics.observe(viewLifecycleOwner, Observer<List<OutputTopicBean>> { newTopics ->
            Timber.i("★★" + newTopics.toString())
            // topic_tableのデータを画面に表示する
//            var outputText = StringBuilder()
//            newTopics.forEach(){
//                outputText.insert(0, "${it.toString()}\n\n")
//            }
//            binding.outputMemo.text = outputText.toString()
        })

        // 送信ボタン押下時の処理
        binding.submitButton.setOnClickListener { view ->
            viewModel.addNewTopics(listOf(
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