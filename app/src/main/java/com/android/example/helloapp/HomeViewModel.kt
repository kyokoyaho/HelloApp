package com.android.example.helloapp

import androidx.lifecycle.ViewModel
import timber.log.Timber

class HomeViewModel: ViewModel() {
    init {
        Timber.i("HomeViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("HomeViewModel destroyed!")
    }
}