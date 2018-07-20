package com.udacity.animal.data.entities.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.os.Parcel;

@Entity
public class AnimeSeries extends BaseSeries {
    @ColumnInfo(name = "episode_count")
    private Integer episodeCount;
    @ColumnInfo(name = "episode_length")
    private Integer episodeLength;
    @ColumnInfo(name = "youtube_video_id")
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

    public AnimeSeries() {
    }

    protected AnimeSeries(Parcel in) {
        super(in);
        this.episodeCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.episodeLength = (Integer) in.readValue(Integer.class.getClassLoader());
        this.youtubeVideoId = in.readString();
    }

    public static final Creator<AnimeSeries> CREATOR = new Creator<AnimeSeries>() {
        @Override
        public AnimeSeries createFromParcel(Parcel source) {
            return new AnimeSeries(source);
        }

        @Override
        public AnimeSeries[] newArray(int size) {
            return new AnimeSeries[size];
        }
    };
}
