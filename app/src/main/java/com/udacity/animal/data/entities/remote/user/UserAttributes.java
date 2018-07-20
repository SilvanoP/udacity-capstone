package com.udacity.animal.data.entities.remote.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.udacity.animal.data.entities.Image;

public class UserAttributes {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("favoritesCount")
    @Expose
    private Integer favoritesCount;
    @SerializedName("reviewsCount")
    @Expose
    private Integer reviewsCount;
    @SerializedName("ratingsCount")
    @Expose
    private Integer ratingsCount;
    @SerializedName("avatar")
    @Expose
    private Image avatar;
    @SerializedName("coverImage")
    @Expose
    private Image coverImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }
}
