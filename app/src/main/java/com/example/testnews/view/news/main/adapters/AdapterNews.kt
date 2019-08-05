package com.example.testnews.view.news.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testnews.R
import com.example.testnews.view.news.main.items.ItemNews
import com.example.testnews.view.news.main.items.ViewHolderInternetFailed
import com.example.testnews.view.news.main.items.ViewHolderItem
import com.example.testnews.view.news.main.items.ViewHolderNews

class AdapterNews (private val list: MutableList<ItemNews>, private val context: Context) : RecyclerView.Adapter<ViewHolderItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {

        return if( viewType == EnumTypeViewHolder.ITEM_NORMAL.ordinal ) {
            ViewHolderNews(LayoutInflater.from(context).inflate(R.layout.view_holder_news, parent, false))
        } else {
            ViewHolderInternetFailed(LayoutInflater.from(context).inflate(R.layout.view_holder_refresh, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type.ordinal
    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        holder.bindView(list[position])
    }

    fun insert(list: List<ItemNews>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun removeLastItem() {
        notifyItemRemoved(list.size)
        if(list.isNotEmpty()) {
            list.removeAt(list.size - 1)
        }
    }

}