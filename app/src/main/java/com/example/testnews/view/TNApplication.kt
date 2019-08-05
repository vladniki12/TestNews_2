package com.example.testnews.view

import android.app.Application
import com.example.testnews.di.networkModule
import com.example.testnews.di.repositoryModule
import com.example.testnews.di.tnAppModule
import com.example.testnews.di.tnDataBaseModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class TNApplication : Application() {

    companion object {
        lateinit var INSTANCE: TNApplication
    }

    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        INSTANCE = this
        startKoin {
            androidContext(this@TNApplication)

            modules( arrayListOf(networkModule, repositoryModule, tnAppModule, tnDataBaseModule))
        }

        initCicerone()
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigationHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }
}