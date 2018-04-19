package com.udacity.animal.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

@Root(name = "entry")
public class AnimeEntry implements Parcelable {

    @Element(required = false)
    private Long id;
    @Element(required = false)
    private String title;
    @Element(required = false)
    private Integer episodes;
    @Element(required = false)
    private Double score;
    @Element(required = false)
    private String type;
    @Element(required = false)
    private String status;
    @Element(name = "start_date", required = false)
    private Date startDate;
    @Element(name = "end_date", required = false)
    private Date endDate;
    @Element(required = false)
    private String synopsis;
    @Element(name = "image", required = false)
    private String imageUrl;

    public AnimeEntry() {
    }

    public AnimeEntry(Parcel in) {
        id = in.readLong();
        title = in.readString();
        episodes = in.readInt();
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

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
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

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeInt(episodes);
        dest.writeDouble(score);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeLong(startDate.getTime());
        dest.writeLong(endDate.getTime());
        dest.writeString(synopsis);
        dest.writeString(imageUrl);
    }

    public static final Parcelable.Creator<AnimeEntry> CREATOR = new Parcelable.Creator<AnimeEntry>() {
        @Override
        public AnimeEntry createFromParcel(Parcel source) {
            return new AnimeEntry(source);
        }

        @Override
        public AnimeEntry[] newArray(int size) {
            return new AnimeEntry[size];
        }
    };
}
