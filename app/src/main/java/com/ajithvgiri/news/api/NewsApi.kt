package com.ajithvgiri.news.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines?country=in")
    fun getTopHeadlines(): Call<ResponseBody>
}