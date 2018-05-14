package com.udacity.animal.data.entities.entries;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "entry")
public class AnimeEntry extends BaseEntry implements Parcelable {

    @Element(required = false)
    private Integer episodes;

    public AnimeEntry() {
    }

    public AnimeEntry(Parcel in) {
        super(in);
        episodes = in.readInt();
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public void writeToParcel(Parcel dest, int i) {
        super.writeToParcel(dest,i);
        dest.writeInt(episodes);
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
