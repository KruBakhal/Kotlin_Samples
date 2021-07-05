
package com.example.kotlin_samples.Model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory__1 {

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
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("star")
    @Expose
    private String star;
    @SerializedName("installed_range")
    @Expose
    private String installedRange;
    @SerializedName("app_link")
    @Expose
    private String appLink;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("image_active")
    @Expose
    private Integer imageActive;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getInstalledRange() {
        return installedRange;
    }

    public void setInstalledRange(String installedRange) {
        this.installedRange = installedRange;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getImageActive() {
        return imageActive;
    }

    public void setImageActive(Integer imageActive) {
        this.imageActive = imageActive;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

}
