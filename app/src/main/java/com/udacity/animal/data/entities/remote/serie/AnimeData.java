package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimeData extends SeriesData {

    @SerializedName("attributes")
    @Expose
    private AnimeAttributes attributes;

    public AnimeAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(AnimeAttributes attributes) {
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

    public AnimeData() {
    }

    protected AnimeData(Parcel in) {
        super(in);
        this.attributes = in.readParcelable(AnimeAttributes.class.getClassLoader());
    }

    public static final Creator<AnimeData> CREATOR = new Creator<AnimeData>() {
        @Override
        public AnimeData createFromParcel(Parcel source) {
            return new AnimeData(source);
        }

        @Override
        public AnimeData[] newArray(int size) {
            return new AnimeData[size];
        }
    };
}
