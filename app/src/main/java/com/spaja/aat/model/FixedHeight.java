package com.spaja.aat.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Spaja on 13-Nov-17.
 */

public class FixedHeight extends RealmObject{

    @SerializedName ("url")
    private String url;

    @SerializedName ("width")
    private String width;

    @SerializedName ("height")
    private String height;

    @SerializedName ("size")
    private String size;

    @SerializedName ("mp4")
    private String mp4;

    @SerializedName ("mp4_size")
    private String mp4Size;

    @SerializedName ("webp")
    private String webp;

    @SerializedName ("webp_size")
    private String webpSize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(String mp4Size) {
        this.mp4Size = mp4Size;
    }

    public String getWebp() {
        return webp;
    }

    public void setWebp(String webp) {
        this.webp = webp;
    }

    public String getWebpSize() {
        return webpSize;
    }

    public void setWebpSize(String webpSize) {
        this.webpSize = webpSize;
    }

}
