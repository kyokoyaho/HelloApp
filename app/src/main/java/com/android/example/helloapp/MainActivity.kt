package com.android.example.helloapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) // TODO ←この行を入れると、画面が白くなり、アクションバーだけ表示される
        drawerLayout = this.findViewById(R.id.drawerLayout)

        // 参考: https://developer.android.com/codelabs/kotlin-android-training-add-navigation?index=..%2F..android-kotlin-fundamentals#7
        // navControllerオブジェクトを取得
        //val navController = this.findNavController(R.id.nav_host_fragment) //←　runtime Exception
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()

        // ナビゲーションドロワーをナビゲーションコントローラーに接続
        val navView = this.findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)

        // navControllerをアプリバーにリンクする
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        Timber.i("test timber")
    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.nav_host_fragment)
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}