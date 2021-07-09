package com.example.kotlin_samples.NetworkClient

import androidx.lifecycle.MutableLiveData
import com.example.kotlin_samples.Model.api.MainResponse

public interface Network_Retrofit_CallBack_Interface {

    public fun onCallbackMainReponnse(list: MutableLiveData<MainResponse>);

    public fun onShowProgress();

    public fun onDimissProgress();

    fun onCanceledProgress(message: String?)


}