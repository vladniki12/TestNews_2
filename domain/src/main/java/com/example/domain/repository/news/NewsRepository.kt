package com.example.domain.repository.news

import com.example.data.api.ArticleResponse
import com.example.data.api.NewsListResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmObject

interface NewsRepository {
    //API
    fun getNewsList(page: Int): Observable<NewsListResponse>

    //DB
    fun getNewsListDB(): Single<List<RealmObject?>>
    fun addList(page: Int, articles: List<ArticleResponse>)
    fun getPageCountSaved(): Int
}