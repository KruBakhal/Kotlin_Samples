
package com.example.kotlin_samples.Model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("app_id")
    @Expose
    private Integer appId;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumb_image")
    @Expose
    private String thumbImage;
    @SerializedName("app_link")
    @Expose
    private String appLink;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("full_thumb_image")
    @Expose
    private String fullThumbImage;
    @SerializedName("splash_active")
    @Expose
    private Integer splashActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFullThumbImage() {
        return fullThumbImage;
    }

    public void setFullThumbImage(String fullThumbImage) {
        this.fullThumbImage = fullThumbImage;
    }

    public Integer getSplashActive() {
        return splashActive;
    }

    public void setSplashActive(Integer splashActive) {
        this.splashActive = splashActive;
    }

}
