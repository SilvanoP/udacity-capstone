package com.udacity.animal.data.entities.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user_anime_join",
        primaryKeys = {"userId", "animeId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"),
                @ForeignKey(entity = AnimeSeries.class, parentColumns = "id", childColumns = "animeId")
        })
public class UserAnime {

    @NonNull
    private Long userId;
    @NonNull
    private Long animeId;

    public UserAnime(@NonNull Long userId, @NonNull Long animeId) {
        this.userId = userId;
        this.animeId = animeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }
}
