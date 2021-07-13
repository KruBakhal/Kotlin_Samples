
package com.example.kotlin_samples.MoreScreen.Model.api;

import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("is_home_enable")
    @Expose
    private Boolean isHomeEnable;
    @SerializedName("app_center")
    @Expose
    private List<AppCenter> appCenter = null;
    @SerializedName("home")
    @Expose
    private List<Home> home = null;
    @SerializedName("more_apps")
    @Expose
    private List<Object> moreApps = null;
    @SerializedName("native_add")
    @Expose
    private NativeAdd nativeAdd;
    @SerializedName("translator_ads_id")
    @Expose
    private TranslatorAdsId translatorAdsId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Boolean getIsHomeEnable() {
        return isHomeEnable;
    }

    public void setIsHomeEnable(Boolean isHomeEnable) {
        this.isHomeEnable = isHomeEnable;
    }

    public List<AppCenter> getAppCenter() {
        return appCenter;
    }

    public void setAppCenter(List<AppCenter> appCenter) {
        this.appCenter = appCenter;
    }

    public List<Home> getHome() {
        return home;
    }

    public void setHome(List<Home> home) {
        this.home = home;
    }

    public List<Object> getMoreApps() {
        return moreApps;
    }

    public void setMoreApps(List<Object> moreApps) {
        this.moreApps = moreApps;
    }

    public NativeAdd getNativeAdd() {
        return nativeAdd;
    }

    public void setNativeAdd(NativeAdd nativeAdd) {
        this.nativeAdd = nativeAdd;
    }

    public TranslatorAdsId getTranslatorAdsId() {
        return translatorAdsId;
    }

    public void setTranslatorAdsId(TranslatorAdsId translatorAdsId) {
        this.translatorAdsId = translatorAdsId;
    }

}
