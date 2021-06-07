package com.android.example.helloapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.android.example.helloapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)

        // 送信ボタン押下時の処理
        binding.submitButton.setOnClickListener { view ->
            binding.outputMemo.text = binding.memoEdittext.text.toString()
            binding.memoEdittext.text.clear()
            hideKeyboard(activity, view)
        }
        return binding.root
    }
}