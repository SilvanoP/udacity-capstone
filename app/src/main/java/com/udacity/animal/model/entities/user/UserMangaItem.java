package com.udacity.animal.model.entities.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;

public class UserMangaItem extends BaseUserItem implements Parcelable {

    @Element(name = "series_mangadb_id", required = false)
    private Long id;
    @Element(name = "series_chapters", required = false)
    private Integer totalChapters;
    @Element(name = "series_volumes", required = false)
    private Integer volumes;
    @Element(name = "my_read_chapters", required = false)
    private Integer readChapters;
    @Element(name = "my_read_volumes", required = false)
    private Integer readVolumes;
    @Element(name = "my_rereadingg", required = false)
    private Integer reread;

    public UserMangaItem() {
    }

    public UserMangaItem(Parcel in) {
        super(in);
        id = in.readLong();
        totalChapters = in.readInt();
        volumes = in.readInt();
        readChapters = in.readInt();
        readVolumes = in.readInt();
        reread = in.readInt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(Integer totalChapters) {
        this.totalChapters = totalChapters;
    }

    public Integer getVolumes() {
        return volumes;
    }

    public void setVolumes(Integer volumes) {
        this.volumes = volumes;
    }

    public Integer getReadChapters() {
        return readChapters;
    }

    public void setReadChapters(Integer readChapters) {
        this.readChapters = readChapters;
    }

    public Integer getReadVolumes() {
        return readVolumes;
    }

    public void setReadVolumes(Integer readVolumes) {
        this.readVolumes = readVolumes;
    }

    public Integer getReread() {
        return reread;
    }

    public void setReread(Integer reread) {
        this.reread = reread;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(id);
        dest.writeInt(totalChapters);
        dest.writeInt(volumes);
        dest.writeInt(readChapters);
        dest.writeInt(readVolumes);
        dest.writeInt(reread);
    }

    public static final Parcelable.Creator<UserMangaItem> CREATOR = new Parcelable.Creator<UserMangaItem>(){
        @Override
        public UserMangaItem createFromParcel(Parcel source) {
            return new UserMangaItem(source);
        }

        @Override
        public UserMangaItem[] newArray(int size) {
            return new UserMangaItem[size];
        }
    };
}
