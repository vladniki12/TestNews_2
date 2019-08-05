package com.example.testnews.view.news.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testnews.R
import com.example.testnews.common.extensions.ui.setVerticalLlm
import com.example.testnews.common.main.TSBaseFragment
import com.example.testnews.view.news.main.adapters.AdapterNews
import com.example.testnews.view.news.main.items.ItemNews
import kotlinx.android.synthetic.main.activity_main_news.*
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager

import android.net.ConnectivityManager
import android.content.Context
import com.example.testnews.view.news.main.adapters.EnumTypeViewHolder


class NewsFragment : TSBaseFragment(), NewsView {
    private val mPresenter: NewsPresenter by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.mvpDelegate = this
    }

    override fun prepareView() {
        context?.let {
            activity?.customToolbar?.setTitle(R.string.news_title)
        }

        newsRecyclerView.setVerticalLlm()
        newsRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.layoutManager?.let { layoutManager ->
                    if( !mPresenter.isLoading() ) {
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val firstVisibleItemPosition =
                            (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 5 && firstVisibleItemPosition >= 0) {
                            mPresenter.getPage()
                        }
                    }
                }
            }
        })

        mPresenter.getPage()
    }

    override fun removeLastItem() {
        val adapter = newsRecyclerView.adapter
        if(adapter != null) {
            if(adapter is AdapterNews) {
                adapter.removeLastItem()
            }
        }
    }

    override fun updateList(list: List<ItemNews>) {
        addList(list)
        hideLoaderFragment()
    }

    private fun addList(list: List<ItemNews>) {
        context?.let {
            val adapter = newsRecyclerView.adapter
            if(adapter == null) {
                newsRecyclerView.adapter = AdapterNews(list.toMutableList(), it)
            } else if(adapter is AdapterNews){
                adapter.insert(list)
            }
        }
    }

    override fun validAccessInternet() {
        if(!verifyAvailableNetwork()) {
            addList(arrayListOf(ItemNews(EnumTypeViewHolder.ITEM_INTERNET_FIALED, null) {
                mPresenter.getPage()
            }))
        }
    }

    private fun verifyAvailableNetwork():Boolean {
        context?.let {
            val connectivityManager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
        return false
    }

    override fun hideLoader() {
        hideLoaderFragment()
    }

    override fun showLoader() {
        showLoaderFragment()
    }

}