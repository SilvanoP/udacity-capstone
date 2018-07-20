package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.udacity.animal.data.entities.Image;

public abstract class SeriesAttributes implements Parcelable {

    @SerializedName("synopsis")
    @Expose
    protected String synopsis;
    @SerializedName("titles")
    @Expose
    protected Titles titles;
    @SerializedName("canonicalTitle")
    @Expose
    protected String canonicalTitle;
    @SerializedName("averageRating")
    @Expose
    protected String averageRating;
    @SerializedName("startDate")
    @Expose
    protected String startDate;
    @SerializedName("endDate")
    @Expose
    protected String endDate;
    @SerializedName("subtype")
    @Expose
    protected String subtype;
    @SerializedName("status")
    @Expose
    protected String status;
    @SerializedName("posterImage")
    @Expose
    protected Image posterImage;
    @SerializedName("coverImage")
    @Expose
    protected Image coverImage;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Titles getTitles() {
        return titles;
    }

    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Image getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(Image posterImage) {
        this.posterImage = posterImage;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.synopsis);
        dest.writeParcelable(this.titles, flags);
        dest.writeString(this.canonicalTitle);
        dest.writeString(this.averageRating);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeString(this.subtype);
        dest.writeString(this.status);
        dest.writeParcelable(this.posterImage, flags);
        dest.writeParcelable(this.coverImage, flags);
    }

    public SeriesAttributes() {
    }

    protected SeriesAttributes(Parcel in) {
        this.synopsis = in.readString();
        this.titles = in.readParcelable(Titles.class.getClassLoader());
        this.canonicalTitle = in.readString();
        this.averageRating = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.subtype = in.readString();
        this.status = in.readString();
        this.posterImage = in.readParcelable(Image.class.getClassLoader());
        this.coverImage = in.readParcelable(Image.class.getClassLoader());
    }
}
