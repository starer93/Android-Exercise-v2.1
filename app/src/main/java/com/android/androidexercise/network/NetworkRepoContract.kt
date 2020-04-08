package com.android.androidexercise.network


import com.android.androidexercise.data.response.NewsListResponse
import io.reactivex.rxjava3.core.Single

interface NetworkRepoContract {
    //get newsList from api
    fun getNewsList(): Single<NewsListResponse>
}