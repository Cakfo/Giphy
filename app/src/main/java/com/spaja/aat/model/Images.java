package com.spaja.aat.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Images extends RealmObject{

    @SerializedName("fixed_width")
    private FixedWidth fixedWidth;

    @SerializedName("original")
    private Original original;

    @SerializedName("original_still")
    private OriginalStill originalStill;

    public OriginalStill getOriginalStill() {
        return originalStill;
    }

    public void setOriginalStill(OriginalStill originalStill) {
        this.originalStill = originalStill;
    }

    public Original getOriginal() {
        return original;
    }

    public void setOriginal(Original original) {
        this.original = original;
    }

    public FixedWidth getFixedWidth() {
        return fixedWidth;
    }

    public void setFixedWidth(FixedWidth fixedWidth) {
        this.fixedWidth = fixedWidth;
    }
}