package com.example.testnews.common.extensions.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setVerticalLlm() {
    val llm = LinearLayoutManager(this.context)
    this.layoutManager = llm
}