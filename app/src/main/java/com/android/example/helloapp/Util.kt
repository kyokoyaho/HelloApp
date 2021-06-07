package com.android.example.helloapp

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hide the keyboard after the user taps the DONE button.
 */
fun hideKeyboard(context: Context?, view: View) {
    context?.let {
        val inputMethodManager =
            it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
