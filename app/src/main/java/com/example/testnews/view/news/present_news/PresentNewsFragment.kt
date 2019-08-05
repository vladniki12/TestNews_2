package com.example.testnews.view.news.present_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testnews.R
import com.example.testnews.common.main.TSBaseFragment
import kotlinx.android.synthetic.main.activity_main_news.*
import kotlinx.android.synthetic.main.fragment_present_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PresentNewsFragment(private val url: String) : TSBaseFragment(), PresentNewsView {
    private val mPresenter: PresentNewsPresenter by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_present_news, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.mvpDelegate = this
    }

    override fun prepareView() {
        context?.let {
            activity?.customToolbar?.setTitle(R.string.present_news_title)

            activity?.customToolbar?.setNavigationIcon(R.drawable.ic_back)

            activity?.customToolbar?.setNavigationOnClickListener {
                mPresenter.back()
            }

            newsWebView.loadUrl(url)
        }
    }
}