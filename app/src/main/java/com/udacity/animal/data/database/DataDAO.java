package com.udacity.animal.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.data.entities.local.UserAnime;
import com.udacity.animal.data.entities.local.UserManga;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface DataDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAnime(AnimeSeries animeSeries);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertManga(MangaSeries mangaSeries);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUserAnime(UserAnime userAnime);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUserManga(UserManga userManga);

    @Update
    void updateUser(User user);
    @Update
    void updateAnime(AnimeSeries animeSeries);
    @Update
    void updateManga(MangaSeries mangaSeries);
    @Update
    void updateUserAnime(UserAnime userAnime);
    @Update
    void updateUserManga(UserManga userManga);

    @Delete
    void deleteUser(User user);
    @Delete
    void deleteAnime(AnimeSeries animeSeries);
    @Delete
    void deleteManga(MangaSeries mangaSeries);
    @Delete
    void deleteUserAnime(UserAnime userAnime);
    @Delete
    void deleteUserManga(UserManga userManga);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    Maybe<User> loadByUsername(String username, String password);

    @Query("SELECT animeseries.* FROM animeseries INNER JOIN USER_ANIME_JOIN ON animeId = animeseries.id " +
            "WHERE userId = :userId")
    Single<List<AnimeSeries>> loadAnimeByUserId(Long userId);

    @Query("SELECT mangaseries.* FROM mangaseries INNER JOIN USER_MANGA_JOIN ON mangaId = mangaseries.id " +
            "WHERE userId = :userId")
    Single<List<MangaSeries>> loadMangaByUserId(Long userId);
}
