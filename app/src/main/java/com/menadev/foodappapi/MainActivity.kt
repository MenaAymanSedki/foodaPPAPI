package com.menadev.foodappapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL="https://api.yelp.com/v3/"
private const val TAG = "MainActivity"
private const val API_KEY="v-tJ-hc2qhs8kWxVh9Jb1vXsU4KIcpwIsj-XZYxNKr9mV-9jCYSlJzYXyW_SQSO7BsNIi1ugZMnZ8-5hCZP0RGtdbZtEzfBNfsI41j8l0MzuUEvPJC9NNq8zjSeLY3Yx"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestrauntsAdapter(this,restaurants)
        rvRestaurant.adapter = adapter



        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

        val yelpService =retrofit.create(YelpServiceInstance::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast","New York").enqueue(object :Callback<YelpSearchResult>{

            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>,
            ) {
                    Log.i(TAG,"onResponse$response")
                val body =response.body()
                if(body == null){
                    Log.w(TAG,"Dia not receive void ")
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG,"onResponse$t")
            }


        })




    }
}