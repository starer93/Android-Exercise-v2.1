package com.android.androidexercise.network.apiServices;

import com.android.androidexercise.data.response.NewsListResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface NewsApi {
    //getting news list
    @GET("s/2iodh4vg0eortkl/facts.js")
    Single<NewsListResponse> getNewsList();
}
