package com.example.kotlin_samples.NetworkClient

import androidx.lifecycle.MutableLiveData
import com.example.kotlin_samples.Model.api.MainResponse

public interface NetworkRetrofitInterface {

    public fun onCallbackMainReponnse(list: MutableLiveData<MainResponse>);
}