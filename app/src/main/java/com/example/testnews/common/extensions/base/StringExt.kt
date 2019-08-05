package com.example.testnews.common.extensions.base

import com.example.testnews.common.utils.DateFormatter


fun String.formatDate(currentMask: String, featureMask: String): String {
    val df = DateFormatter.generateFormatterDate(currentMask)
    val result = df.parse(this)
    return try {
        val simpleDateFormat = DateFormatter.generateFormatterDate(featureMask)

        val date = simpleDateFormat.format(result)
        date
    } catch (e: Throwable) {
        ""
    }
}