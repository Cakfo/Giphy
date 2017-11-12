package com.spaja.aat.model;

import io.realm.RealmObject;

/**
 * Created by Spaja on 11-Nov-17.
 */

public class FixedWidthStill extends RealmObject{

    private String url;
    private String width;
    private String height;

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
}
