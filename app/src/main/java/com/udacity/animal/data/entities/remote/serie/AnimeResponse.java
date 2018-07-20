package com.udacity.animal.data.entities.remote.serie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class used only for Gson Mapping of the Json Response
 */
public class AnimeResponse {

    @SerializedName("data")
    @Expose
    private AnimeData data;

    public AnimeData getData() {
        return data;
    }

    public void setData(AnimeData data) {
        this.data = data;
    }
}
