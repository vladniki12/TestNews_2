package com.example.domain

import com.example.data.Constants
import com.example.data.api.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TNServiceApi {

    @GET("everything")
    fun getNewsList(@Query("q") q: String = Constants.Network.Q,
                    @Query("from") from: String = Constants.Network.FROM,
                    @Query("sortBy") sortBy: String = Constants.Network.SORT_BY,
                    @Query("apiKey") apiKey: String = Constants.Network.API_KEY,
                    @Query("page") page: String
                    ): Observable<NewsListResponse>
}