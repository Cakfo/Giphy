package com.spaja.aat.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Spaja on 03-Nov-17.
 */

public class ApiResponse extends RealmObject {

    @SerializedName("data")
    private RealmList<GifData> gifData;

    public RealmList<GifData> getGifData() {
        return gifData;
    }

    public void setGifData(RealmList<GifData> gifData) {
        this.gifData = gifData;
    }
}
