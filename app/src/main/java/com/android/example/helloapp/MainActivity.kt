package com.android.example.helloapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 参考: https://developer.android.com/codelabs/kotlin-android-training-add-navigation?index=..%2F..android-kotlin-fundamentals#7
        // navControllerオブジェクトを取得
        // val navController = this.findNavController(R.id.nav_host_fragment) ←　runtime Exception
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
        // navControllerをアプリバーにリンクする
        NavigationUI.setupActionBarWithNavController(this,navController)

        Timber.i("test timber")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }
}