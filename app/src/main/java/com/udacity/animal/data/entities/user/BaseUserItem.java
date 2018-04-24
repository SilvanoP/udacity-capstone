package com.udacity.animal.data.entities.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * The base class related to the list of anime e manga that the user has in his/her profile.
 */
public abstract class BaseUserItem implements Parcelable {

    @Element(name = "series_title", required = false)
    protected String title;
    @Element(name = "series_type", required = false)
    protected String type;
    @Element(name = "series_status", required = false)
    protected String status;
    @Element(name = "series_start", required = false)
    protected Date seriesStartDate;
    @Element(name = "series_end", required = false)
    protected Date seriesEndDate;
    @Element(name = "series_image", required = false)
    protected String imageUrl;
    @Element(name = "my_start_date", required = false)
    protected Date startedDate;
    @Element(name = "my_finish_date", required = false)
    protected Date finishedDate;
    @Element(name = "my_score", required = false)
    protected Integer userScore;
    @Element(name = "my_status", required = false)
    protected Integer userStatus;

    public BaseUserItem() {
    }

    public BaseUserItem(Parcel in) {
        title = in.readString();
        type = in.readString();
        status = in.readString();
        seriesStartDate = new Date(in.readLong());
        seriesEndDate = new Date(in.readLong());
        imageUrl = in.readString();
        startedDate = new Date(in.readLong());
        finishedDate = new Date(in.readLong());
        userScore = in.readInt();
        userStatus = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSeriesStartDate() {
        return seriesStartDate;
    }

    public void setSeriesStartDate(Date seriesStartDate) {
        this.seriesStartDate = seriesStartDate;
    }

    public Date getSeriesEndDate() {
        return seriesEndDate;
    }

    public void setSeriesEndDate(Date seriesEndDate) {
        this.seriesEndDate = seriesEndDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeLong(seriesStartDate.getTime());
        dest.writeLong(seriesEndDate.getTime());
        dest.writeString(imageUrl);
        dest.writeLong(startedDate.getTime());
        dest.writeLong(finishedDate.getTime());
        dest.writeInt(userScore);
        dest.writeInt(userStatus);
    }
}
