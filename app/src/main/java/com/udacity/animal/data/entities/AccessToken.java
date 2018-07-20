package com.udacity.animal.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessToken implements Parcelable {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("token_type")
    @Expose
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.accessToken);
        dest.writeValue(this.createdAt);
        dest.writeValue(this.expiresIn);
        dest.writeString(this.refreshToken);
        dest.writeString(this.scope);
        dest.writeString(this.tokenType);
    }

    public AccessToken() {
    }

    protected AccessToken(Parcel in) {
        this.accessToken = in.readString();
        this.createdAt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.expiresIn = (Integer) in.readValue(Integer.class.getClassLoader());
        this.refreshToken = in.readString();
        this.scope = in.readString();
        this.tokenType = in.readString();
    }

    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator<AccessToken>() {
        @Override
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        @Override
        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };
}
