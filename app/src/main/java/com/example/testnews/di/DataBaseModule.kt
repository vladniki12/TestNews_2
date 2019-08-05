package com.example.testnews.di

import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.dsl.module

val tnDataBaseModule = module {
    single { provideRealmConfiguration() }
    single { provideRealm(get()) }
}


 fun provideRealmConfiguration(): RealmConfiguration {
    return RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded()
        .build()
}


internal fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
    return Realm.getInstance(realmConfiguration)
}


