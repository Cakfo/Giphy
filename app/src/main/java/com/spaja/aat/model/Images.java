package com.spaja.aat.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Images extends RealmObject{

    @SerializedName("fixed_width")
    private FixedWidth fixedWidth;

    public FixedWidth getFixedWidth() {
        return fixedWidth;
    }

    public void setFixedWidth(FixedWidth fixedWidth) {
        this.fixedWidth = fixedWidth;
    }
}