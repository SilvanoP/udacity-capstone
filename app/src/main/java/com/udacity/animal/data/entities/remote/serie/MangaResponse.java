package com.udacity.animal.data.entities.remote.serie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MangaResponse {

    @SerializedName("data")
    @Expose
    private MangaData data;

    public MangaData getData() {
        return data;
    }

    public void setData(MangaData data) {
        this.data = data;
    }
}
