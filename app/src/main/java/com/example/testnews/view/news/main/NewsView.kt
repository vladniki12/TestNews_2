package com.example.testnews.view.news.main

import com.arellomobile.mvp.MvpView
import com.example.testnews.view.news.main.items.ItemNews

interface NewsView : MvpView {
    fun updateList(list: List<ItemNews>)
    fun validAccessInternet()
    fun removeLastItem()

    fun hideLoader()
    fun showLoader()
}