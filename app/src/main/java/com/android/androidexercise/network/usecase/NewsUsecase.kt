package com.android.androidexercise.network.usecase

import com.android.androidexercise.base.BaseUsecase
import com.android.androidexercise.data.response.NewsListResponse
import com.android.androidexercise.network.NetworkRepo
import io.reactivex.rxjava3.core.Single

class NewsUsecase : BaseUsecase<NewsListResponse>() {
    private val networkRepo = NetworkRepo()
    override fun createObservable(): Single<NewsListResponse> = networkRepo.getNewsList()

}