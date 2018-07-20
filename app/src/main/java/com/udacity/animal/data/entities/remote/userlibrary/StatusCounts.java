package com.udacity.animal.data.entities.remote.userlibrary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusCounts {

    @SerializedName("current")
    @Expose
    private Integer current;
    @SerializedName("planned")
    @Expose
    private Integer planned;
    @SerializedName("completed")
    @Expose
    private Integer completed;
    @SerializedName("onHold")
    @Expose
    private Integer onHold;
    @SerializedName("dropped")
    @Expose
    private Integer dropped;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPlanned() {
        return planned;
    }

    public void setPlanned(Integer planned) {
        this.planned = planned;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getOnHold() {
        return onHold;
    }

    public void setOnHold(Integer onHold) {
        this.onHold = onHold;
    }

    public Integer getDropped() {
        return dropped;
    }

    public void setDropped(Integer dropped) {
        this.dropped = dropped;
    }
}
