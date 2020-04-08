package com.android.androidexercise.ui.news

import com.android.androidexercise.base.BasePresenter
import com.android.androidexercise.network.usecase.NewsUsecase

class NewsPresenter : BasePresenter<NewsView>() {
    private val newsUsecase = NewsUsecase()

    //making api call
    fun getNewsList() {
        newsUsecase.observe(
            onSuccess = {
                view?.onNewsSucceed(it)
            },
            onError = {
                view?.showError(it.e.toString())
            }
        ).register(this)
    }

}