package com.udacity.animal.data.entities.entries;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "entry")
public class MangaEntry extends BaseEntry implements Parcelable {

    @Element(required = false)
    private Integer chapters;
    @Element(required = false)
    private Integer volumes;

    public MangaEntry() {
    }

    public MangaEntry(Parcel in) {
        super(in);
        chapters = in.readInt();
        volumes = in.readInt();
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

    public void writeToParcel(Parcel dest, int i) {
        super.writeToParcel(dest, i);
        dest.writeInt(chapters);
        dest.writeInt(volumes);
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
