package com.udacity.animal.data.entities.user;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "anime")
public class UserAnimeItem extends BaseUserItem implements Parcelable {

    @Element(name = "series_animedb_id", required = false)
    private Long id;
    @Element(name = "series_episodes", required = false)
    private Integer totalEpisodes;
    @Element(name = "my_watched_episodes", required = false)
    private Integer watchedEpisodes;
    @Element(name = "my_rewatching", required = false)
    private Integer rewatch;

    public UserAnimeItem() {
    }

    public UserAnimeItem(Parcel in) {
        super(in);
        id = in.readLong();
        totalEpisodes = in.readInt();
        watchedEpisodes = in.readInt();
        rewatch = in.readInt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(Integer totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public Integer getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public void setWatchedEpisodes(Integer watchedEpisodes) {
        this.watchedEpisodes = watchedEpisodes;
    }

    public Integer getRewatch() {
        return rewatch;
    }

    public void setRewatch(Integer rewatch) {
        this.rewatch = rewatch;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(id);
        dest.writeInt(totalEpisodes);
        dest.writeInt(watchedEpisodes);
        dest.writeInt(rewatch);
    }

    public static final Parcelable.Creator<UserAnimeItem> CREATOR = new Parcelable.Creator<UserAnimeItem>() {
        @Override
        public UserAnimeItem createFromParcel(Parcel source) {
            return new UserAnimeItem(source);
        }

        @Override
        public UserAnimeItem[] newArray(int size) {
            return new UserAnimeItem[size];
        }
    };
}
