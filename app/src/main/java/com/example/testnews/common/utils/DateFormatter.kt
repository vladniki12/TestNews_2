package com.example.testnews.common.utils

import java.text.SimpleDateFormat

class DateFormatter {
    companion object {
        fun generateFormatterDate(pattern: String): SimpleDateFormat {
            return SimpleDateFormat(pattern)
        }
    }
}