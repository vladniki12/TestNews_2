package com.example.domain.repository.news

import com.example.data.api.ArticleResponse
import com.example.data.api.NewsListResponse
import com.example.data.db.ArticleDB
import com.example.data.db.PageDB
import com.example.domain.TNServiceApi
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject

class NewsRepositoryImpl(private val realm: Realm, private val restApi: TNServiceApi) : NewsRepository {
    override fun getNewsList(page: Int): Observable<NewsListResponse> {
        return restApi.getNewsList(page = page.toString())
    }

    override fun addList(page: Int, articles: List<ArticleResponse>) {
        if(page > getPageCountSaved()) {
            val item = realm.where(PageDB::class.java).findFirst()
            realm.executeTransaction {
                item!!.page += 1
                articles.forEach {
                    val article = it.convertToDB()
                    val id = realm.where(ArticleDB::class.java).count()
                    article.id = id.toInt() + 1
                    realm.copyToRealmOrUpdate(item)
                    realm.insertOrUpdate(article)
                }
            }
        }
    }

    override fun getNewsListDB(): Single<List<RealmObject?>> {
        val items = realm.where(ArticleDB::class.java).findAll()
        return Single.just(items)
    }

    override fun getPageCountSaved(): Int {
        val item = realm.where(PageDB::class.java).findFirst()
        if(item == null) {
            realm.executeTransaction {
                val pageDB = PageDB()
                pageDB.page = 0
                realm.insertOrUpdate(pageDB)
            }
            return 0
        }
        return item.page
    }
}