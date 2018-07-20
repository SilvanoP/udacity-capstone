package com.udacity.animal.data.entities.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user_manga_join",
        primaryKeys = {"userId", "mangaId"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"),
                @ForeignKey(entity = MangaSeries.class, parentColumns = "id", childColumns = "mangaId")
        })
public class UserManga {

    @NonNull
    private Long userId;
    @NonNull
    private Long mangaId;

    public UserManga(@NonNull Long userId, @NonNull Long mangaId) {
        this.userId = userId;
        this.mangaId = mangaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMangaId() {
        return mangaId;
    }

    public void setMangaId(Long mangaId) {
        this.mangaId = mangaId;
    }
}
