package com.example.testnews.common.extensions.ui

import android.view.View

fun View.onClick(handler: () -> Unit) {
    setOnClickListener {
        handler()
    }
}