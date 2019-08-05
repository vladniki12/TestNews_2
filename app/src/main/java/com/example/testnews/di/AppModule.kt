package com.example.testnews.di

import com.example.testnews.view.news.main.NewsPresenter
import com.example.testnews.view.news.present_news.PresentNewsPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val tnAppModule = module {
    viewModel {
        NewsPresenter(get())
    }

    viewModel {
        PresentNewsPresenter()
    }
}