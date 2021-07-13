
package com.example.kotlin_samples.MoreScreen.Model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NativeAdd {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("playstore_link")
    @Expose
    private String playstoreLink;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("image_active")
    @Expose
    private Integer imageActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlaystoreLink() {
        return playstoreLink;
    }

    public void setPlaystoreLink(String playstoreLink) {
        this.playstoreLink = playstoreLink;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

}
