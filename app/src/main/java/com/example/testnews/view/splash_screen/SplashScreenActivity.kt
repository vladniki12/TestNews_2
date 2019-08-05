package com.example.testnews.view.splash_screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.data.Constants
import com.example.testnews.R
import com.example.testnews.view.news.NewsActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            runOnUiThread { startNews() }
        }, Constants.Utils.TIMER_DELAY)
    }

    private fun startNews() {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
        finish()
    }

}