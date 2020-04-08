package com.android.androidexercise.network

import com.android.androidexercise.network.apiServices.NewsApi
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient private constructor() {
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val newsApi: NewsApi
        get() {
            return retrofit.create(NewsApi::class.java)
        }


    private object CLIENT {
        val INSTANCE = NetworkClient()
    }

    companion object {
        private const val BASE_URL =
            "https://dl.dropboxusercontent.com/" //HARD CODE BASE_URL, should place it in build file
        val instance: NetworkClient by lazy { CLIENT.INSTANCE } //Singleton for network client
    }
}
