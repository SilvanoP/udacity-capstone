package com.udacity.animal.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.data.entities.local.UserAnime;
import com.udacity.animal.data.entities.local.UserManga;

@Database(entities = {User.class, AnimeSeries.class, MangaSeries.class, UserAnime.class, UserManga.class},
version = 1)
@TypeConverters({com.udacity.animal.data.database.TypeConverters.class})
public abstract class AnimalDatabase extends RoomDatabase {
    public abstract DataDAO dataDAO();
}
