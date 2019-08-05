package com.example.testnews.common.main

import androidx.lifecycle.ViewModel
import com.arellomobile.mvp.MvpView
import com.example.testnews.view.TNApplication
import io.reactivex.disposables.CompositeDisposable

abstract class TNBasePresenter <View: MvpView>: ViewModel() {
    var mvpDelegate: View? = null
    var compositionDisposable = CompositeDisposable()


    fun back() {
        TNApplication.INSTANCE.getRouter().exit()
    }
}