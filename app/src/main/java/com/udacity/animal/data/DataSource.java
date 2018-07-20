package com.udacity.animal.data;

import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface DataSource {

    /*interface AuthenticatorCallback {
        void onReceiveAuthentication(User user);
        void onAuthenticationFailed();
    }

    interface ListSeriesCallback {
        void onReceiveListAnimes(List<AnimeSeries> animes);
        void onReceiveListMangas(List<MangaSeries> mangas);

        void onReceiveFailed();
    }*/

    Maybe<User> onLogin(String username, String email, String password);

    Single<List<BaseSeries>> getListSeries(Boolean isAnime, Long userId);
    Single<List<BaseSeries>> onSearchSeriesByTitle(Boolean isAnime, String search);
    Single<List<BaseSeries>> onSearchSeriesByUserStatus(Boolean isAnime, String status);

    void saveSeries(BaseSeries series, boolean isAnime);
    void updateSeries(BaseSeries series, boolean isAnime);
    void deleteSeries(BaseSeries series, boolean isAnime);
}
