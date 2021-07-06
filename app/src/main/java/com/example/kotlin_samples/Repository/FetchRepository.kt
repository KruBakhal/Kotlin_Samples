package com.example.kotlin_samples.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_samples.Model.api.MainResponse
import com.example.kotlin_samples.NetworkClient.NetworkRetrofitInterface
import com.example.kotlin_samples.NetworkClient.NetwrokRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class FetchRepository {


    lateinit var networkInterface: NetworkRetrofitInterface


    constructor(param: NetworkRetrofitInterface) {
        networkInterface = param
        fecthDataFromNetwork()
    }

    companion object {
        var fetchRepository: FetchRepository? = null

        fun getFetchRepositoryInstance(param: NetworkRetrofitInterface): FetchRepository? {
            if (fetchRepository == null) {
                fetchRepository = FetchRepository(param)
            }
            return fetchRepository;
        }

    }

    fun fecthDataFromNetwork() {
        var mainData = NetwrokRetrofit.createNetworkInstance()
            .getData("com.latest.status.message.text.jokes.funny");

        mainData.enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                var list = MutableLiveData<MainResponse>()
                list.value = response.body()
                networkInterface.onCallbackMainReponnse(list)
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)
            }
        })
    }

}