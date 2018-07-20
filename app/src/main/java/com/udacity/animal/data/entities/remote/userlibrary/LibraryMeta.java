package com.udacity.animal.data.entities.remote.userlibrary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryMeta {

    @SerializedName("statusCounts")
    @Expose
    private StatusCounts statusCounts;

    public StatusCounts getStatusCounts() {
        return statusCounts;
    }

    public void setStatusCounts(StatusCounts statusCounts) {
        this.statusCounts = statusCounts;
    }
}
