package com.example.kotlin_samples.MoreScreen.Service.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_samples.MoreScreen.Model.api.MainResponse
import com.example.kotlin_samples.MoreScreen.Service.Callback.Network_Retrofit_CallBack_Interface
import com.example.kotlin_samples.MoreScreen.Service.NetworkClient.NetwrokRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class APIFetchService {


    lateinit var networkInterface: Network_Retrofit_CallBack_Interface
    var fetchRepository: APIFetchService? = null
    var cout: Int = 0;

    fun getFetchRepositoryInstance(param: Network_Retrofit_CallBack_Interface): APIFetchService? {
        if (fetchRepository == null) {
            fetchRepository = APIFetchService()
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