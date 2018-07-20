package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MangaData extends SeriesData {

    @SerializedName("attributes")
    @Expose
    private MangaAttributes attributes;

    public MangaAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(MangaAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.attributes, flags);
    }

    public MangaData() {
    }

    protected MangaData(Parcel in) {
        super(in);
        this.attributes = in.readParcelable(MangaAttributes.class.getClassLoader());
    }

    public static final Creator<MangaData> CREATOR = new Creator<MangaData>() {
        @Override
        public MangaData createFromParcel(Parcel source) {
            return new MangaData(source);
        }

        @Override
        public MangaData[] newArray(int size) {
            return new MangaData[size];
        }
    };
}
