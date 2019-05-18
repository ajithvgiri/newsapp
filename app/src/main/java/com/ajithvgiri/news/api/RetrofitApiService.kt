package com.ajithvgiri.news.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiService {

    private const val BASE_URL = "https://newsapi.org/v2/"
    private var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Authorization", "Bearer ")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            val client = httpClient.build()
            return if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                retrofit
            } else {
                null
            }

        }
}