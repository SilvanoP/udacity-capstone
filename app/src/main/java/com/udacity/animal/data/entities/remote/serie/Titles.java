package com.udacity.animal.data.entities.remote.serie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Titles implements Parcelable {

    @SerializedName("en")
    @Expose
    private String en;
    @SerializedName("en_jp")
    @Expose
    private String enJp;
    @SerializedName("ja_jp")
    @Expose
    private String jaJp;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getEnJp() {
        return enJp;
    }

    public void setEnJp(String enJp) {
        this.enJp = enJp;
    }

    public String getJaJp() {
        return jaJp;
    }

    public void setJaJp(String jaJp) {
        this.jaJp = jaJp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.en);
        dest.writeString(this.enJp);
        dest.writeString(this.jaJp);
    }

    public Titles() {
    }

    protected Titles(Parcel in) {
        this.en = in.readString();
        this.enJp = in.readString();
        this.jaJp = in.readString();
    }

    public static final Parcelable.Creator<Titles> CREATOR = new Parcelable.Creator<Titles>() {
        @Override
        public Titles createFromParcel(Parcel source) {
            return new Titles(source);
        }

        @Override
        public Titles[] newArray(int size) {
            return new Titles[size];
        }
    };
}
