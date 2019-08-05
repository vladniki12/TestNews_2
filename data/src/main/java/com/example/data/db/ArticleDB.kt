package com.example.data.db

import com.example.data.api.ArticleResponse
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ArticleDB: RealmObject() {
    @PrimaryKey
    var id = 0
    var title: String = ""
    var description: String = ""
    var url: String = ""
    var urlToImage: String? = ""
    var publishedAt: String = ""

    fun convertToArticleResponse(): ArticleResponse {
        return ArticleResponse(title, description, url, urlToImage, publishedAt)
    }
}