package com.example.kotlin_samples.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_samples.Model.api.MainResponse
import com.example.kotlin_samples.NetworkClient.Network_Retrofit_CallBack_Interface
import com.example.kotlin_samples.NetworkClient.NetwrokRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class FetchRepository {


    lateinit var networkInterface: Network_Retrofit_CallBack_Interface
    var fetchRepository: FetchRepository? = null
    var cout: Int = 0;

    fun getFetchRepositoryInstance(param: Network_Retrofit_CallBack_Interface): FetchRepository? {
        if (fetchRepository == null) {
            fetchRepository = FetchRepository()
            networkInterface = param
            fecthDataFromNetwork()
            cout++
            Log.d("TAG", "getMainResponse: $cout")
        }
        return fetchRepository;
    }


    fun fecthDataFromNetwork() {
        networkInterface.onShowProgress()

        var mainData = NetwrokRetrofit().createNetworkInstance()
            .getData("com.latest.status.message.text.jokes.funny");

        mainData.enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                var list = MutableLiveData<MainResponse>()
                list.value = response.body()
                networkInterface.onDimissProgress()
                networkInterface.onCallbackMainReponnse(list)

            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: " + t.message)
                networkInterface.onCanceledProgress(t.message)
            }
        })

    }

}