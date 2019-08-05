package com.example.data

interface Constants {
    interface Utils {
        companion object {
            const val TIMER_DELAY = 300L
            const val ANIMATION_SPEED = 2
            const val DIM_AMOUNT = 0.5f
        }
    }

    interface Network {
        companion object {
            const val SERVER_BASE_URL = "https://newsapi.org/v2/"
            const val Q = "android"
            const val FROM = "2019-04-00"
            const val SORT_BY = "publishedAt"
            const val API_KEY = "26eddb253e7840f988aec61f2ece2907"
        }
    }

    interface Masks{
        companion object {
            const val  INPUT_DATE_MASk = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            const val  OUTPUT_DATE_MASk = "dd.MM.yy HH:mm"
        }
    }

}