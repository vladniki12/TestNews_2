package com.example.testnews.view.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testnews.R
import com.example.testnews.common.main.Screens
import com.example.testnews.view.TNApplication
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Replace

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_news)

        navigator.applyCommands(arrayOf(Replace(Screens.News.NewsScreen())))
    }

    private val navigator = object : SupportAppNavigator(this, R.id.rootActivityLayout) {
    }

    override fun onResume() {
        super.onResume()
        TNApplication.INSTANCE.getNavigationHolder().setNavigator(navigator)
    }

    override fun onPause() {
        TNApplication.INSTANCE.getNavigationHolder().removeNavigator()

        super.onPause()
    }
}