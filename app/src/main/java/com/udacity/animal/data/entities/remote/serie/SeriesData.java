package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class SeriesData implements Parcelable {

    @SerializedName("id")
    @Expose
    protected String id;
    @SerializedName("type")
    @Expose
    protected String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
    }

    public SeriesData() {
    }

    protected SeriesData(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
    }
}
