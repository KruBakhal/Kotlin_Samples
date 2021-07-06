package com.example.kotlin_samples.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_samples.Model.api.MainResponse
import com.example.kotlin_samples.NetworkClient.NetworkRetrofitInterface

public class FetchLiveData : ViewModel(), NetworkRetrofitInterface {

    var responseMainMutableLiveData: MutableLiveData<MainResponse>? = null
    var internetConnectivity: MutableLiveData<Boolean>? = null
    var fetchRespostry: FetchRepository? = null

    public fun getMainResponse(): LiveData<MainResponse>? {
        if (responseMainMutableLiveData == null) {
            responseMainMutableLiveData = MutableLiveData<MainResponse>()
            fetchRespostry = FetchRepository.getFetchRepositoryInstance(this)
        }
        return responseMainMutableLiveData;
    }

    public fun getMainResponseData(): LiveData<MainResponse>? {
        return responseMainMutableLiveData
    }

    override fun onCallbackMainReponnse(list: MutableLiveData<MainResponse>) {
        responseMainMutableLiveData?.value = list.value
    }

    override fun onCleared() {
        super.onCleared()
        fetchRespostry = null
    }
}



