package com.udacity.animal.data.entities.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.udacity.animal.data.entities.Image;

import java.util.Calendar;

public abstract class BaseSeries implements Parcelable {

    @PrimaryKey
    protected Long id;
    protected String type;
    protected String synopsis;
    @ColumnInfo(name = "title_japanese")
    protected String titleJapanese;
    @ColumnInfo(name = "title_english")
    protected String titleEnglish;
    @ColumnInfo(name = "title_romanji")
    protected String titleRomanji;
    @ColumnInfo(name = "canonical_title")
    protected String canonicalTitle;
    @ColumnInfo(name = "average_rating")
    protected Double averageRating;
    @ColumnInfo(name = "start_date")
    protected Calendar startDate;
    @ColumnInfo(name = "end_date")
    protected Calendar endDate;
    protected String subtype;
    protected String status;
    @Embedded(prefix = "poster_")
    protected Image posterImage;
    @Embedded(prefix = "cover_")
    protected Image coverImage;
    @ColumnInfo(name = "user_status")
    protected String userStatus;
    protected Integer progress;
    protected String notes;
    @ColumnInfo(name = "progressed_at")
    protected Calendar progressedAt;
    @ColumnInfo(name = "started_at")
    protected Calendar startedAt;
    @ColumnInfo(name = "finished_at")
    protected Calendar finishedAt;
    @ColumnInfo(name = "rating_twenty")
    protected Integer ratingTwenty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitleJapanese(String titleJapanese) {
        this.titleJapanese = titleJapanese;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleRomanji() {
        return titleRomanji;
    }

    public void setTitleRomanji(String titleRomanji) {
        this.titleRomanji = titleRomanji;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Calendar getProgressedAt() {
        return progressedAt;
    }

    public void setProgressedAt(Calendar progressedAt) {
        this.progressedAt = progressedAt;
    }

    public Calendar getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Calendar startedAt) {
        this.startedAt = startedAt;
    }

    public Calendar getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Calendar finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getRatingTwenty() {
        return ratingTwenty;
    }

    public void setRatingTwenty(Integer ratingTwenty) {
        this.ratingTwenty = ratingTwenty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.type);
        dest.writeString(this.synopsis);
        dest.writeString(this.titleJapanese);
        dest.writeString(this.titleEnglish);
        dest.writeString(this.titleRomanji);
        dest.writeString(this.canonicalTitle);
        dest.writeDouble(this.averageRating);
        dest.writeSerializable(this.startDate);
        dest.writeSerializable(this.endDate);
        dest.writeString(this.subtype);
        dest.writeString(this.status);
        dest.writeParcelable(this.posterImage, flags);
        dest.writeParcelable(this.coverImage, flags);
        dest.writeString(this.userStatus);
        dest.writeValue(this.progress);
        dest.writeString(this.notes);
        dest.writeSerializable(this.progressedAt);
        dest.writeSerializable(this.startedAt);
        dest.writeSerializable(this.finishedAt);
        dest.writeValue(this.ratingTwenty);
    }

    public BaseSeries() {
    }

    protected BaseSeries(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.type = in.readString();
        this.synopsis = in.readString();
        this.titleJapanese = in.readString();
        this.titleEnglish = in.readString();
        this.titleRomanji = in.readString();
        this.canonicalTitle = in.readString();
        this.averageRating = in.readDouble();
        this.startDate = (Calendar) in.readSerializable();
        this.endDate = (Calendar) in.readSerializable();
        this.subtype = in.readString();
        this.status = in.readString();
        this.posterImage = in.readParcelable(Image.class.getClassLoader());
        this.coverImage = in.readParcelable(Image.class.getClassLoader());
        this.userStatus = in.readString();
        this.progress = (Integer) in.readValue(Integer.class.getClassLoader());
        this.notes = in.readString();
        this.progressedAt = (Calendar) in.readSerializable();
        this.startedAt = (Calendar) in.readSerializable();
        this.finishedAt = (Calendar) in.readSerializable();
        this.ratingTwenty = (Integer) in.readValue(Integer.class.getClassLoader());
    }
}
