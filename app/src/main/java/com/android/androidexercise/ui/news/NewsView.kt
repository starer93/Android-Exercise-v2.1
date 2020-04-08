package com.android.androidexercise.ui.news

import com.android.androidexercise.base.BaseView
import com.android.androidexercise.data.response.NewsListResponse

interface NewsView : BaseView {
    //pass response to view
    fun onNewsSucceed(response: NewsListResponse)
}