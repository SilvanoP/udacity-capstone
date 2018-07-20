package com.udacity.animal.data.entities.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.os.Parcel;

@Entity
public class MangaSeries extends BaseSeries {
    @ColumnInfo(name = "chapter_count")
    private Integer chapterCount;
    @ColumnInfo(name = "volume_count")
    private Integer volumeCount;
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

    public MangaSeries() {
    }

    protected MangaSeries(Parcel in) {
        super(in);
        this.chapterCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.volumeCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.serialization = in.readString();
    }

    public static final Creator<MangaSeries> CREATOR = new Creator<MangaSeries>() {
        @Override
        public MangaSeries createFromParcel(Parcel source) {
            return new MangaSeries(source);
        }

        @Override
        public MangaSeries[] newArray(int size) {
            return new MangaSeries[size];
        }
    };
}
