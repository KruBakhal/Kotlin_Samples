package com.example.kotlin_samples.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_samples.Model.api.MainResponse
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

public class FetchLiveData : ViewModel() {

    public var responseMainMutableLiveData: MutableLiveData<MainResponse>? = null

    public fun getMainResponse(): LiveData<MainResponse>? {
        if (responseMainMutableLiveData == null) {
            responseMainMutableLiveData = MutableLiveData<MainResponse>()
            var fetchRepository = FetchRepository.getFetchRepositoryInstance()

        }
        return responseMainMutableLiveData;
    }

    public fun getMainResponseData(): LiveData<MainResponse>? {
        return responseMainMutableLiveData
    }
}



