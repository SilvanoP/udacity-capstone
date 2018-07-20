package com.udacity.animal.data;

import android.content.Context;
import android.text.TextUtils;

import com.udacity.animal.Source;
import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.util.Constants;
import com.udacity.animal.util.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DataRepository implements DataSource {

    private Context mContext;
    private DataSource mLocalDataSource;
    private DataSource mRemoteDataSource;

    private List<AnimeSeries> animeSeriesCache;
    private List<MangaSeries> mangaSeriesCache;

    @Inject
    DataRepository(@Source(Constants.DataSource.REMOTE) DataSource remoteDataSource,
                   @Source(Constants.DataSource.LOCAL) DataSource localDataSource,
                   Context context) {
        this.mContext = context;
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;

        animeSeriesCache = new ArrayList<>();
        mangaSeriesCache = new ArrayList<>();
    }

    @Override
    public Maybe<User> onLogin(String username, String email, String password) {
        // TODO Shared Prefs for auto login

        if (Utils.isOnline(mContext)) {
            return mRemoteDataSource.onLogin(username, email, password);
        }

        return mLocalDataSource.onLogin(username, email, password);
    }

    @Override
    public Single<List<BaseSeries>> getListSeries(final Boolean isAnime, Long userId) {
        List<BaseSeries> baseSeries;
        if (isAnime && animeSeriesCache != null && animeSeriesCache.size() > 0) {
            baseSeries = new ArrayList<BaseSeries>(animeSeriesCache);
            return Single.just(baseSeries);
        } else if (!isAnime && mangaSeriesCache != null && mangaSeriesCache.size() > 0) {
            baseSeries = new ArrayList<BaseSeries>(mangaSeriesCache);
            return Single.just(baseSeries);
        }

        if (Utils.isOnline(mContext)) {
            return mRemoteDataSource.getListSeries(isAnime, userId).flatMap(new Function<List<BaseSeries>, SingleSource<List<BaseSeries>>>() {
                @Override
                public SingleSource<List<BaseSeries>> apply(List<BaseSeries> baseSeries) {
                    // Save on database
                    cacheSeries(isAnime, baseSeries);
                    return Single.just(baseSeries);
                }
            });
        }

        return mLocalDataSource.getListSeries(isAnime, userId).flatMap(new Function<List<BaseSeries>, SingleSource<List<BaseSeries>>>() {
            @Override
            public SingleSource<List<BaseSeries>> apply(List<BaseSeries> baseSeries) {
                cacheSeries(isAnime, baseSeries);
                return Single.just(baseSeries);
            }
        });
    }

    private void cacheSeries(Boolean isAnime, List<BaseSeries> baseSeries) {
        if (isAnime) {
            animeSeriesCache.clear();
            for (BaseSeries series : baseSeries) {
                animeSeriesCache.add((AnimeSeries) series);
            }
        } else {
            mangaSeriesCache.clear();
            for (BaseSeries series : baseSeries) {
                mangaSeriesCache.add((MangaSeries) series);
            }
        }
    }

    @Override
    public Single<List<BaseSeries>> onSearchSeriesByTitle(Boolean isAnime, final String search) {
        if (isAnime && animeSeriesCache != null && animeSeriesCache.size() > 0) {
            return Single.just(animeSeriesCache).flatMap(new Function<List<AnimeSeries>, SingleSource<List<BaseSeries>>>() {
                @Override
                public SingleSource<List<BaseSeries>> apply(List<AnimeSeries> animeSeries) {
                    List<BaseSeries> found = new ArrayList<>();
                    for (AnimeSeries series : animeSeriesCache) {
                        if (series.getCanonicalTitle().contains(search)) {
                            found.add(series);
                        }
                    }

                    return Single.just(found);
                }
            });
        } else if (!isAnime && mangaSeriesCache != null && mangaSeriesCache.size() > 0) {
            return Single.just(animeSeriesCache).flatMap(new Function<List<AnimeSeries>, SingleSource<List<BaseSeries>>>() {
                @Override
                public SingleSource<List<BaseSeries>> apply(List<AnimeSeries> animeSeries) {
                    List<BaseSeries> found = new ArrayList<>();
                    for (AnimeSeries series : animeSeriesCache) {
                        if (series.getCanonicalTitle().contains(search)) {
                            found.add(series);
                        }
                    }

                    return Single.just(found);
                }
            });
        }

        return null;
    }

    @Override
    public Single<List<BaseSeries>> onSearchSeriesByUserStatus(Boolean isAnime, final String status) {
        if (isAnime && animeSeriesCache != null && animeSeriesCache.size() > 0) {
            return Single.just(animeSeriesCache).flatMap(new Function<List<AnimeSeries>, SingleSource<List<BaseSeries>>>() {
                @Override
                public SingleSource<List<BaseSeries>> apply(List<AnimeSeries> animeSeries) {
                    List<BaseSeries> found = new ArrayList<>();
                    for (AnimeSeries series : animeSeriesCache) {
                        if (series.getUserStatus().equals(status)) {
                            found.add(series);
                        }
                    }

                    return Single.just(found);
                }
            });
        } else if (!isAnime && mangaSeriesCache != null && mangaSeriesCache.size() > 0) {
            return Single.just(animeSeriesCache).flatMap(new Function<List<AnimeSeries>, SingleSource<List<BaseSeries>>>() {
                @Override
                public SingleSource<List<BaseSeries>> apply(List<AnimeSeries> animeSeries) {
                    List<BaseSeries> found = new ArrayList<>();
                    for (AnimeSeries series : animeSeriesCache) {
                        if (series.getUserStatus().equals(status)) {
                            found.add(series);
                        }
                    }

                    return Single.just(found);
                }
            });
        }

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
