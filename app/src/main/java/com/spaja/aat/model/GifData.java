package com.spaja.aat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Spaja on 03-Nov-17.
 */

public class GifData extends RealmObject {

    @SerializedName ("type")
    @Expose
    private String type;

    @PrimaryKey
    @SerializedName ("id")
    @Expose
    private String id;

    @SerializedName ("slug")
    @Expose
    private String slug;
    @SerializedName ("url")
    @Expose
    private String url;
    @SerializedName ("bitly_gif_url")
    @Expose
    private String bitlyGifUrl;
    @SerializedName ("bitly_url")
    @Expose
    private String bitlyUrl;
    @SerializedName ("embed_url")
    @Expose
    private String embedUrl;
    @SerializedName ("username")
    @Expose
    private String username;
    @SerializedName ("source")
    @Expose
    private String source;
    @SerializedName ("rating")
    @Expose
    private String rating;
    @SerializedName ("content_url")
    @Expose
    private String contentUrl;
    @SerializedName ("source_tld")
    @Expose
    private String sourceTld;
    @SerializedName ("source_post_url")
    @Expose
    private String sourcePostUrl;
    @SerializedName ("is_indexable")
    @Expose
    private int isIndexable;
    @SerializedName ("import_datetime")
    @Expose
    private String importDatetime;
    @SerializedName ("trending_datetime")
    @Expose
    private String trendingDatetime;

    private String title;

    private Images images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBitlyGifUrl() {
        return bitlyGifUrl;
    }

    public void setBitlyGifUrl(String bitlyGifUrl) {
        this.bitlyGifUrl = bitlyGifUrl;
    }

    public String getBitlyUrl() {
        return bitlyUrl;
    }

    public void setBitlyUrl(String bitlyUrl) {
        this.bitlyUrl = bitlyUrl;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSourceTld() {
        return sourceTld;
    }

    public void setSourceTld(String sourceTld) {
        this.sourceTld = sourceTld;
    }

    public String getSourcePostUrl() {
        return sourcePostUrl;
    }

    public void setSourcePostUrl(String sourcePostUrl) {
        this.sourcePostUrl = sourcePostUrl;
    }

    public int getIsIndexable() {
        return isIndexable;
    }

    public void setIsIndexable(int isIndexable) {
        this.isIndexable = isIndexable;
    }

    public String getImportDatetime() {
        return importDatetime;
    }

    public void setImportDatetime(String importDatetime) {
        this.importDatetime = importDatetime;
    }

    public String getTrendingDatetime() {
        return trendingDatetime;
    }

    public void setTrendingDatetime(String trendingDatetime) {
        this.trendingDatetime = trendingDatetime;
    }
}

