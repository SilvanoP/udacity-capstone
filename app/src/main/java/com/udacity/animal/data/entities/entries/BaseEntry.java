package com.udacity.animal.data.entities.entries;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * The entries are an automatic way for the SimpleXML to convert the xml response
 * from the MAL api search to an object.
 *
 * There are two possible entries, AnimeEntry and MangaEntry.
 */
public abstract class BaseEntry implements Parcelable {

    @Element(required = false)
    protected Long id;
    @Element(required = false)
    protected String title;
    @Element(required = false)
    protected Double score;
    @Element(required = false)
    protected String type;
    @Element(required = false)
    protected String status;
    @Element(name = "start_date", required = false)
    protected Date startDate;
    @Element(name = "end_date", required = false)
    protected Date endDate;
    @Element(required = false)
    protected String synopsis;
    @Element(name = "image", required = false)
    protected String imageUrl;

    public BaseEntry() {
    }

    public BaseEntry(Parcel in) {
        id = in.readLong();
        title = in.readString();
        score = in.readDouble();
        type = in.readString();
        status = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        synopsis = in.readString();
        imageUrl = in.readString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeDouble(score);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeLong(startDate.getTime());
        dest.writeLong(endDate.getTime());
        dest.writeString(synopsis);
        dest.writeString(imageUrl);
    }
}
