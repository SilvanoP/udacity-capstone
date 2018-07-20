package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimeAttributes extends SeriesAttributes {


    @SerializedName("episodeCount")
    @Expose
    private Integer episodeCount;
    @SerializedName("episodeLength")
    @Expose
    private Integer episodeLength;
    @SerializedName("youtubeVideoId")
    @Expose
    private String youtubeVideoId;

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public Integer getEpisodeLength() {
        return episodeLength;
    }

    public void setEpisodeLength(Integer episodeLength) {
        this.episodeLength = episodeLength;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.episodeCount);
        dest.writeValue(this.episodeLength);
        dest.writeString(this.youtubeVideoId);
    }

    public AnimeAttributes() {
    }

    protected AnimeAttributes(Parcel in) {
        super(in);
        this.episodeCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.episodeLength = (Integer) in.readValue(Integer.class.getClassLoader());
        this.youtubeVideoId = in.readString();
    }

    public static final Creator<AnimeAttributes> CREATOR = new Creator<AnimeAttributes>() {
        @Override
        public AnimeAttributes createFromParcel(Parcel source) {
            return new AnimeAttributes(source);
        }

        @Override
        public AnimeAttributes[] newArray(int size) {
            return new AnimeAttributes[size];
        }
    };
}
