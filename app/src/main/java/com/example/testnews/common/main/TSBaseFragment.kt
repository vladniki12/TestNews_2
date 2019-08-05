package com.example.testnews.common.main

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.data.Constants
import com.kaopiz.kprogresshud.KProgressHUD

abstract class TSBaseFragment : MvpAppCompatFragment(), PreparableView {

    private lateinit var loader: KProgressHUD

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loader = KProgressHUD.create(activity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(true)
            .setAnimationSpeed(Constants.Utils.ANIMATION_SPEED)
            .setDimAmount(Constants.Utils.DIM_AMOUNT)
        prepareView()
    }

    fun hideLoaderFragment() {
        loader.dismiss()
    }

    fun showLoaderFragment() {
        loader.show()
    }
}