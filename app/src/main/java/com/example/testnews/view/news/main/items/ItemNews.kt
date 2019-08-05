package com.example.testnews.view.news.main.items

import com.example.data.api.ArticleResponse
import com.example.testnews.view.news.main.adapters.EnumTypeViewHolder

class ItemNews(
    var type: EnumTypeViewHolder,
    var article: ArticleResponse?,
    var onClick: ((String)->Unit)?
)