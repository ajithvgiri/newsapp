package com.ajithvgiri.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ajithvgiri.news.api.NewsApi
import com.ajithvgiri.news.api.RetrofitApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val service = RetrofitApiService.client?.create(NewsApi::class.java)

        service?.getTopHeadlines()?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    println("this is the response from api ${response.body()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }
}
