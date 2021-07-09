package com.example.kotlin_samples.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kotlin_samples.Model.api.MainResponse
import com.example.kotlin_samples.MoreAppActivity
import com.example.kotlin_samples.NetworkClient.Network_Retrofit_CallBack_Interface

public class FetchLiveData() : ViewModel(), Network_Retrofit_CallBack_Interface {

    public enum class ProgressUIType {
        SHOW, DIMISS, CANCEL
    }

    var responseMainMutableLiveData: MutableLiveData<MainResponse>? = null
    var internetConnectivity: MutableLiveData<Boolean>? = null
    var connecLiveData: ConnectionLiveData? = null
    var showProgressBar: MutableLiveData<ProgressUIType>? = null

    fun getMainResponse(): LiveData<MainResponse>? {
        if (responseMainMutableLiveData == null || responseMainMutableLiveData?.value?.home == null) {
            responseMainMutableLiveData = null
            responseMainMutableLiveData = MutableLiveData<MainResponse>()

            if (internetConnectivity?.value == true) {

                FetchRepository().getFetchRepositoryInstance(this)
            } else {
                responseMainMutableLiveData = null
                showProgressBar?.value = ProgressUIType.CANCEL
            }
        }
        return responseMainMutableLiveData;
    }

    fun getMainResponseData(): LiveData<MainResponse>? {
        return responseMainMutableLiveData
    }

    fun getInternetConnectivity(): LiveData<Boolean>? {
        if (internetConnectivity == null) {
            internetConnectivity = MutableLiveData<Boolean>(true)

        }

        return internetConnectivity
    }

    fun getShowProgressStatus(): LiveData<ProgressUIType>? {
        if (showProgressBar == null)
            showProgressBar = MutableLiveData<ProgressUIType>(ProgressUIType.CANCEL)
        return showProgressBar
    }


    override fun onCallbackMainReponnse(list: MutableLiveData<MainResponse>) {
//        responseMainMutableLiveData = MutableLiveData<MainResponse>()
        responseMainMutableLiveData.apply { responseMainMutableLiveData?.value = list.value }
    }

    override fun onShowProgress() {
        showProgressBar?.value = ProgressUIType.SHOW
    }

    override fun onDimissProgress() {
        showProgressBar?.value = ProgressUIType.DIMISS
    }

    override fun onCanceledProgress(message: String?) {
        showProgressBar?.value = ProgressUIType.CANCEL
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun setInterNetConnectivity_Context(context: MoreAppActivity) {
        connecLiveData = ConnectionLiveData(context);
        connecLiveData!!.observe(context, Observer { connection ->
            if (connection.getIsConnected()) {
                when (connection.getType()) {
                    0 -> {
                        internetConnectivity?.value = true
                    }
                    1 -> {
                        internetConnectivity?.value = true
                    }
                }
            } else {
                internetConnectivity?.value = false


            }
        })
    }

    fun retryCall() {

        showProgressBar?.value = ProgressUIType.SHOW
        if (internetConnectivity?.value == true) {
            FetchRepository().getFetchRepositoryInstance(this)
        } else {
            showProgressBar?.value = ProgressUIType.CANCEL
        }

    }
}



