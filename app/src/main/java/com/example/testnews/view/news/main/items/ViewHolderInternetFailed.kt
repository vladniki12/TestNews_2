package com.example.testnews.view.news.main.items

import android.view.View
import com.example.testnews.common.extensions.ui.onClick
import kotlinx.android.synthetic.main.view_holder_refresh.view.*

class ViewHolderInternetFailed(val view: View) : ViewHolderItem(view) {

    override fun bindView(item: ItemNews) {
        view.refreshButton.onClick {
            item.onClick?.let {
                it("")
            }
        }
    }
}