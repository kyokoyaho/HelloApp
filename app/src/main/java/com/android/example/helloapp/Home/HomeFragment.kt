package com.android.example.helloapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.helloapp.R
import com.android.example.helloapp.databinding.FragmentHomeBinding
import com.android.example.helloapp.domain.InputTopicBean
import com.android.example.helloapp.domain.OutputTopicBean
import com.android.example.helloapp.hideKeyboard

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
        val adapter = TopicAdapter(viewModel)
        binding.topicList.adapter = adapter

        // binding
        binding.viewModel = viewModel

        // ViewModelクラスを監視する
        viewModel.navigateToAddTopic.observe(viewLifecycleOwner,
            { navigate ->
                if(navigate) {
                    // AddTopic画面へ遷移
                    findNavController().navigate(R.id.action_homeFragment_to_addTopicFragment)
                    viewModel.onNavigateToAddTopic()
                }
            })
        viewModel.topics.observe(viewLifecycleOwner, Observer<List<OutputTopicBean>> {
            it?.let {
                // RecyclerViewのデータを更新
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}