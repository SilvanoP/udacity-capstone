package com.udacity.animal.data.entities.remote.userlibrary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryAttributes {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("progress")
    @Expose
    private Integer progress;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("progressedAt")
    @Expose
    private String progressedAt;
    @SerializedName("startedAt")
    @Expose
    private String startedAt;
    @SerializedName("finishedAt")
    @Expose
    private String finishedAt;
    @SerializedName("ratingTwenty")
    @Expose
    private Integer ratingTwenty;
    private Boolean isAnime;

    public Boolean getAnime() {
        return isAnime;
    }

    public void setAnime(Boolean anime) {
        isAnime = anime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProgressedAt() {
        return progressedAt;
    }

    public void setProgressedAt(String progressedAt) {
        this.progressedAt = progressedAt;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getRatingTwenty() {
        return ratingTwenty;
    }

    public void setRatingTwenty(Integer ratingTwenty) {
        this.ratingTwenty = ratingTwenty;
    }
}
