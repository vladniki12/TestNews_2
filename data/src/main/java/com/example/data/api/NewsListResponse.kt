package com.example.data.api

import com.google.gson.annotations.SerializedName

data class NewsListResponse( @SerializedName("status") val status: String,
                             @SerializedName("totalResults") val totalResults: Int,
                             @SerializedName("articles") val articles: List<ArticleResponse>
 )