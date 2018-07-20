package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MangaAttributes extends SeriesAttributes {

    @SerializedName("chapterCount")
    @Expose
    private Integer chapterCount;
    @SerializedName("volumeCount")
    @Expose
    private Integer volumeCount;
    @SerializedName("serialization")
    @Expose
    private String serialization;

    public Integer getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
    }

    public Integer getVolumeCount() {
        return volumeCount;
    }

    public void setVolumeCount(Integer volumeCount) {
        this.volumeCount = volumeCount;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.chapterCount);
        dest.writeValue(this.volumeCount);
        dest.writeString(this.serialization);
    }

    public MangaAttributes() {
    }

    protected MangaAttributes(Parcel in) {
        super(in);
        this.chapterCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.volumeCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.serialization = in.readString();
    }

    public static final Creator<MangaAttributes> CREATOR = new Creator<MangaAttributes>() {
        @Override
        public MangaAttributes createFromParcel(Parcel source) {
            return new MangaAttributes(source);
        }

        @Override
        public MangaAttributes[] newArray(int size) {
            return new MangaAttributes[size];
        }
    };
}
