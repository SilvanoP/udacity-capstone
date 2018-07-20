package com.udacity.animal.data.entities.remote.userlibrary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("attributes")
    @Expose
    private LibraryAttributes attributes;

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

    public LibraryAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(LibraryAttributes attributes) {
        this.attributes = attributes;
    }
}
