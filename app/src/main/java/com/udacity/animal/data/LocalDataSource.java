package com.udacity.animal.data;

import android.content.Context;

import com.udacity.animal.data.database.AnimalDatabase;
import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class LocalDataSource implements DataSource {

    private AnimalDatabase mDatabase;

    @Inject
    LocalDataSource(AnimalDatabase database) {
        this.mDatabase = database;
    }

    @Override
    public Maybe<User> onLogin(final String username, final String email, final String password) {
        return mDatabase.dataDAO().loadByUsername(username, password);
    }

    @Override
    public Single<List<BaseSeries>> getListSeries(Boolean isAnime, Long userId) {
        if (isAnime) {
            return mDatabase.dataDAO().loadAnimeByUserId(userId).flatMap(new Function<List<AnimeSeries>, SingleSource<? extends List<BaseSeries>>>() {
                @Override
                public SingleSource<? extends List<BaseSeries>> apply(List<AnimeSeries> animeSeries) throws Exception {
                    return Single.just(new ArrayList<BaseSeries>(animeSeries));
                }
            });
        }

        return mDatabase.dataDAO().loadMangaByUserId(userId).flatMap(new Function<List<MangaSeries>, SingleSource<? extends List<BaseSeries>>>() {
            @Override
            public SingleSource<? extends List<BaseSeries>> apply(List<MangaSeries> mangaSeries) throws Exception {
                return Single.just(new ArrayList<BaseSeries>(mangaSeries));
            }
        });
    }

    @Override
    public Single<List<BaseSeries>> onSearchSeriesByTitle(Boolean isAnime, String search) {
        return null;
    }

    @Override
    public Single<List<BaseSeries>> onSearchSeriesByUserStatus(Boolean isAnime, String status) {
        return null;
    }

    @Override
    public void saveSeries(BaseSeries series, boolean isAnime) {

    }

    @Override
    public void updateSeries(BaseSeries series, boolean isAnime) {

    }

    @Override
    public void deleteSeries(BaseSeries series, boolean isAnime) {

    }
}
