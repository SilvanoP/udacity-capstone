package com.udacity.animal.data.entities.remote.userlibrary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LibraryResponse {

    @SerializedName("data")
    @Expose
    private List<LibraryData> data = null;
    @SerializedName("meta")
    @Expose
    private LibraryMeta meta;

    public List<LibraryData> getData() {
        return data;
    }

    public void setData(List<LibraryData> data) {
        this.data = data;
    }

    public LibraryMeta getMeta() {
        return meta;
    }

    public void setMeta(LibraryMeta meta) {
        this.meta = meta;
    }
}
