package com.example.testnews.view.news.main.items

import android.view.View
import com.example.data.Constants
import com.example.testnews.common.extensions.base.formatDate
import com.example.testnews.common.extensions.ui.onClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_news.view.*

class ViewHolderNews(val view: View) : ViewHolderItem(view){
    override fun bindView(item: ItemNews) {
        item.article?.let {article ->
            article.urlToImage?.let {
                if (it.isNotEmpty())
                    Picasso.get().load(it).into(view.newsImageView)
            }
            view.titleTextView.text = article.title
            view.descriptionTextView.text = article.description
            view.dateTextView.text = article.publishedAt.formatDate(Constants.Masks.INPUT_DATE_MASk, Constants.Masks.OUTPUT_DATE_MASk)
            view.onClick {
                item.onClick?.let {
                    it(article.url)
                }
            }
        }
    }
}