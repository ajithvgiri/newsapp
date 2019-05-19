package com.ajithvgiri.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ajithvgiri.news.api.NewsApi
import com.ajithvgiri.news.api.RetrofitApiService
import com.ajithvgiri.news.model.Headlines
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val service = RetrofitApiService.client?.create(NewsApi::class.java)



        FirebaseMessagingService



        service?.getTopHeadlines()?.enqueue(object : Callback<Headlines> {
            override fun onFailure(call: Call<Headlines>, t: Throwable) {
                println("onFailure")
            }

            override fun onResponse(call: Call<Headlines>, response: Response<Headlines>) {
                if (response.isSuccessful) {
                    println("this is the response from api ${response.body()?.totalResults}")

                    totalResults.text = "total results ${response.body()?.totalResults} "

                }
            }

        })


        service?.getSouces()?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    println("this the response for getSource ${response.body()?.string()}")
                }
            }

        })


        Glide.with(this).load("https://akm-img-a-in.tosshub.com/indiatoday/images/story/201905/Redmi_Note_7_1_0-647x363.png?3hsRhw90zbxiVc.haibbowAwKnhIOifu").into(imageView)
    }


}
