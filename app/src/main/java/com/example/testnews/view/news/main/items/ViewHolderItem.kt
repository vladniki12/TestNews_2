package com.example.testnews.view.news.main.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindView(item: ItemNews)
}