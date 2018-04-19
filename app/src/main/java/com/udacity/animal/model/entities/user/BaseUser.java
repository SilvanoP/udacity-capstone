package com.udacity.animal.model.entities.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;

/**
 * My Anime List has to separate profiles for anime and manga statistics
 * This is the common part between profiles
 */
public abstract class BaseUser implements Parcelable {

    @Element(name = "user_id", required = false)
    protected Long id;
    @Element(name = "user_name", required = false)
    protected String username;
    @Element(name = "user_completed", required = false)
    protected Integer completed;
    @Element(name = "user_onhold", required = false)
    protected Integer onHold;
    @Element(name = "user_dropped", required = false)
    protected Integer dropped;
    @Element(name = "user_days_spent_watching", required = false)
    protected Double daysSpent;

    public BaseUser() {
    }

    public BaseUser(Parcel in) {
        id = in.readLong();
        username = in.readString();
        completed = in.readInt();
        onHold = in.readInt();
        dropped = in.readInt();
        daysSpent = in.readDouble();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Double getDaysSpent() {
        return daysSpent;
    }

    public void setDaysSpent(Double daysSpent) {
        this.daysSpent = daysSpent;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(username);
        dest.writeInt(completed);
        dest.writeInt(onHold);
        dest.writeInt(dropped);
        dest.writeDouble(daysSpent);
    }
}
