package com.example.testnews.di

import com.example.data.Constants
import com.example.domain.TNServiceApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideLoggingInterceptor() }
    single{ provideGson() }

    single{ provideDefaultOkHttpClient(get()) }
    single{ provideRetrofit(get(), get()) }
    single{ provideKSService(get()) }

}

fun provideDefaultOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.Network.SERVER_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}

fun provideGson(): Gson {
    return GsonBuilder()
        .serializeNulls()
        .create()
}

fun provideKSService(retrofit: Retrofit): TNServiceApi = retrofit.create(TNServiceApi::class.java)

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}
