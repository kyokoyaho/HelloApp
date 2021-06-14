package com.android.example.helloapp.addTopic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.helloapp.R
import com.android.example.helloapp.databinding.FragmentAddTopicBinding
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.hideKeyboard

class AddTopicFragment : Fragment() {

    private lateinit var viewModel: AddTopicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddTopicBinding>(inflater,
            R.layout.fragment_add_topic,container,false)

        // ViewModelを作成
        viewModel = ViewModelProvider(this).get(AddTopicViewModel::class.java)

        // 送信ボタン押下時の処理
        binding.submitButton.setOnClickListener { view ->
            viewModel.addNewTopics(listOf(
                InputTopicBean(note = binding.memoEdittext.text.toString())
            ))
            // 入力フォームを空にする
            binding.memoEdittext.text.clear()
            // キーボードを非表示にする
            hideKeyboard(activity, view)
            // Home画面へ遷移
            findNavController().navigate(R.id.action_addTopicFragment_to_homeFragment)
        }
        return binding.root
    }
}