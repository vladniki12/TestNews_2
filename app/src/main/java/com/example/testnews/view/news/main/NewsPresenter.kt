package com.example.testnews.view.news.main

import com.example.data.db.ArticleDB
import com.example.domain.repository.news.NewsRepository
import com.example.testnews.common.main.Screens
import com.example.testnews.common.main.TNBasePresenter
import com.example.testnews.view.TNApplication
import com.example.testnews.view.news.main.adapters.EnumTypeViewHolder
import com.example.testnews.view.news.main.items.ItemNews
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsPresenter(private val newsRepository: NewsRepository) : TNBasePresenter<NewsView>() {




    private var page = 1

    private var isLoading = false
    private var isFailedRequest = false
    private var isLoadDB = false

    fun getPage() {
        val contPage = newsRepository.getPageCountSaved()
        if(contPage == 0) {
            mvpDelegate?.showLoader()
        }
        if(contPage  < page ) {

            isLoading = true
            compositionDisposable.add(
                newsRepository
                    .getNewsList(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (isFailedRequest) {
                            mvpDelegate?.removeLastItem()
                        }
                        isLoading = false
                        isFailedRequest = false
                        if (it.articles.isNotEmpty()) {
                            page += 1
                        }
                        val listItems = mutableListOf<ItemNews>()
                        newsRepository.addList(page - 1, it.articles)
                        it.articles.forEach { item ->
                            listItems.add(ItemNews(EnumTypeViewHolder.ITEM_NORMAL, item) { url ->
                                TNApplication.INSTANCE.getRouter().navigateTo(Screens.News.PresentNewsScreen(url))
                            })
                        }
                        mvpDelegate?.updateList(listItems)

                    }, {
                        if (!isFailedRequest) {
                            mvpDelegate?.validAccessInternet()
                        }
                        isLoading = false
                        isFailedRequest = true

                    })
            )
        } else if(!isLoadDB){
            isLoadDB = true
            page = newsRepository.getPageCountSaved() + 1
            compositionDisposable.add(
                newsRepository
                    .getNewsListDB()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { items, errors ->
                        if(errors == null) {
                            val listItems = mutableListOf<ItemNews>()
                            items.forEach { itemDB ->
                                if(itemDB is ArticleDB) {
                                    listItems.add(ItemNews(EnumTypeViewHolder.ITEM_NORMAL,  itemDB.convertToArticleResponse()){
                                        TNApplication.INSTANCE.getRouter().navigateTo(Screens.News.PresentNewsScreen(it))
                                    })
                                }
                            }
                            mvpDelegate?.updateList(listItems)
                        }
                    }
            )
        }
    }

    fun isLoading(): Boolean {
        return isLoading
    }
}