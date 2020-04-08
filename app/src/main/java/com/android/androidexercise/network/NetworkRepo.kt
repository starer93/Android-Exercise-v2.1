package com.android.androidexercise.network

import com.android.androidexercise.data.response.NewsListResponse
import io.reactivex.rxjava3.core.Single


//repo for all network apis
class NetworkRepo : NetworkRepoContract {

    private val api = NetworkClient.instance.newsApi

    override fun getNewsList(): Single<NewsListResponse> {
        return api.newsList
    }
}