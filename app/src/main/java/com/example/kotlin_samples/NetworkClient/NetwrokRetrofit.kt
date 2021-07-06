package com.example.kotlin_samples.NetworkClient

import android.graphics.Movie
import com.example.kotlin_samples.Model.api.MainResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

public class NetwrokRetrofit {

    companion object {

        var BASE_URL = "http://161.35.119.227/artwork_cache/api/AdvertiseNewApplications/17/"

        fun createNetworkInstance(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

    interface ApiInterface {

        @GET("{packageName}")
        fun getData(@Path("packageName") str: String): Call<MainResponse>
    }
}