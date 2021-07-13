package com.example.kotlin_samples.MoreScreen.Service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kotlin_samples.MoreScreen.Model.api.MainResponse
import com.example.kotlin_samples.MoreScreen.View.MoreAppActivity
import com.example.kotlin_samples.MoreScreen.Service.NetworkClient.Network_Retrofit_CallBack_Interface
import com.example.kotlin_samples.MoreScreen.Service.Repository.APIFetchService
import com.example.kotlin_samples.MoreScreen.Service.Repository.InternetConnectivityLiveData

public class FetchDataRepository() : ViewModel(), Network_Retrofit_CallBack_Interface {

    public enum class ProgressUIType {
        SHOW, DIMISS, CANCEL
    }

    var responseMainMutableLiveData: MutableLiveData<MainResponse>? = null
    var internetConnectivity: MutableLiveData<Boolean>? = null
    var connecLiveData: InternetConnectivityLiveData? = null
    var showProgressBar: MutableLiveData<ProgressUIType>? = null

    fun getMainResponse(): LiveData<MainResponse>? {
        if (responseMainMutableLiveData == null || responseMainMutableLiveData?.value?.home == null) {
            responseMainMutableLiveData = null
            responseMainMutableLiveData = MutableLiveData<MainResponse>()

            if (internetConnectivity?.value == true) {

                APIFetchService().getFetchRepositoryInstance(this)
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
        connecLiveData =
            InternetConnectivityLiveData(
                context
            );
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
            APIFetchService().getFetchRepositoryInstance(this)
        } else {
            showProgressBar?.value = ProgressUIType.CANCEL
        }

    }
}



