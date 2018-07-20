package com.udacity.animal.data.entities.remote.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used only for Gson Mapping of the Json Response
 */
public class UserResponse {

    @SerializedName("data")
    @Expose
    private List<UserData> userData;

    public UserResponse() {
        userData = new ArrayList<>();
    }

    public List<UserData> getUserData() {
        return userData;
    }

    public void setUserData(List<UserData> userData) {
        this.userData = userData;
    }
}
