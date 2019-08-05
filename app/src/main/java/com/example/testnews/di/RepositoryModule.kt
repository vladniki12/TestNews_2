package com.example.testnews.di

import com.example.domain.repository.news.NewsRepository
import com.example.domain.repository.news.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}