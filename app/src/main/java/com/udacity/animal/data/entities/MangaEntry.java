package com.udacity.animal.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

@Root(name = "entry")
public class MangaEntry implements Parcelable {

    @Element(required = false)
    private Long id;
    @Element(required = false)
    private String title;
    @Element(required = false)
    private Integer chapters;
    @Element(required = false)
    private Integer volumes;
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

    public MangaEntry() {
    }

    public MangaEntry(Parcel in) {
        id = in.readLong();
        title = in.readString();
        chapters = in.readInt();
        volumes = in.readInt();
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

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public Integer getVolumes() {
        return volumes;
    }

    public void setVolumes(Integer volumes) {
        this.volumes = volumes;
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
        dest.writeInt(chapters);
        dest.writeInt(volumes);
        dest.writeDouble(score);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeLong(startDate.getTime());
        dest.writeLong(endDate.getTime());
        dest.writeString(synopsis);
        dest.writeString(imageUrl);
    }

    public static final Parcelable.Creator<MangaEntry> CREATOR = new Parcelable.Creator<MangaEntry>() {
        @Override
        public MangaEntry createFromParcel(Parcel source) {
            return new MangaEntry(source);
        }

        @Override
        public MangaEntry[] newArray(int size) {
            return new MangaEntry[size];
        }
    };
}
