package com.example.data.api

import com.example.data.db.ArticleDB
import com.google.gson.annotations.SerializedName

data class ArticleResponse (@SerializedName("title") val title: String,
                              @SerializedName("description") val description: String,
                              @SerializedName("url") val url: String,
                              @SerializedName("urlToImage") val urlToImage: String?,
                              @SerializedName("publishedAt") val publishedAt: String
) {
    fun convertToDB(): ArticleDB {
        val item = ArticleDB()
        item.title = title
        item.description = description
        item.url = url
        item.urlToImage = urlToImage
        item.publishedAt = publishedAt
        return item
    }
}