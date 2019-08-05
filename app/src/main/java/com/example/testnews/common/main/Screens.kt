package com.example.testnews.common.main

import androidx.fragment.app.Fragment
import com.example.testnews.view.news.main.NewsFragment
import com.example.testnews.view.news.present_news.PresentNewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface Screens {
    interface News {
        class NewsScreen : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return NewsFragment()
            }
        }

        class PresentNewsScreen(private val url: String) : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return PresentNewsFragment(url)
            }
        }
    }
}