package com.udacity.animal.data;

import android.support.annotation.NonNull;

import com.udacity.animal.data.entities.AccessToken;
import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.data.entities.remote.serie.AnimeResponse;
import com.udacity.animal.data.entities.remote.serie.MangaResponse;
import com.udacity.animal.data.entities.remote.user.UserData;
import com.udacity.animal.data.entities.remote.user.UserResponse;
import com.udacity.animal.data.entities.remote.userlibrary.LibraryData;
import com.udacity.animal.data.entities.remote.userlibrary.LibraryResponse;
import com.udacity.animal.data.services.AuthorizationInterceptor;
import com.udacity.animal.data.services.KitsuClient;
import com.udacity.animal.util.Constants;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {

    private final static String KIND_ANIME = "anime";
    private final static String KIND_MANGA = "manga";

    private KitsuClient client;
    private AuthorizationInterceptor interceptor;

    @Inject
    RemoteDataSource(KitsuClient client, AuthorizationInterceptor interceptor) {
        this.client = client;
        this.interceptor = interceptor;
    }

    @Override
    public Maybe<User> onLogin(final String username, final String email, final String password) {
        // First it needs to get the access token
        return client.getToken("password", email, password, Constants.CLIENT_ID,
                Constants.CLIENT_SECRET)
                .flatMap(new Function<AccessToken, SingleSource<UserResponse>>() {
                    @Override
                    public SingleSource<UserResponse> apply(AccessToken token) throws Exception {
                        if (token != null) {
                            // Give the access token to the inteceptor for future requests
                            interceptor.setAccessToken(token.getAccessToken());
                        }
                        // Then searchs for user
                        return client.getUser(username);
                    }
                })
                .flatMap(new Function<UserResponse, SingleSource<User>>() {
                    @Override
                    public SingleSource<User> apply(UserResponse userResponse) throws Exception {
                        User user = null;
                        // Converts the Remote User to Local User
                        if (userResponse != null && userResponse.getUserData().size() > 0) {
                            UserData ud = userResponse.getUserData().get(0);
                            user = DataUtils.convertUserFromRemoteToLocal(ud, null, username, password);
                        }
                        return Single.just(user);
                    }
                })
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        // Returns the User or empty
                        return user != null;
                    }
                });
    }

    @Override
    public Single<List<BaseSeries>> getListSeries(final Boolean isAnime, Long userId) {
            String kind = KIND_ANIME;
            if (!isAnime) {
                kind = KIND_MANGA;
                return client.getUserLibrary(String.valueOf(userId), kind)
                        .flatMapObservable(new Function<LibraryResponse, ObservableSource<List<LibraryData>>>() {
                            @Override
                            public ObservableSource<List<LibraryData>> apply(LibraryResponse libraryResponse) {
                                return Observable.just(libraryResponse.getData());
                            }
                        })
                        .flatMapIterable(new Function<List<LibraryData>, Iterable<LibraryData>>() {
                            @Override
                            public Iterable<LibraryData> apply(List<LibraryData> libraryData) {
                                return libraryData;
                            }
                        })
                        .flatMap(new Function<LibraryData, ObservableSource<MangaResponse>>() {
                            @Override
                            public ObservableSource<MangaResponse> apply(LibraryData libraryData) {
                                return client.getMangaById(libraryData.getId()).toObservable();
                            }
                        }, new BiFunction<LibraryData, MangaResponse, BaseSeries>() {
                            @Override
                            public BaseSeries apply(LibraryData libraryData, MangaResponse mangaResponse) {
                                return DataUtils.convertMangaFromRemoteToLocal(libraryData, mangaResponse.getData());
                            }
                        })
                        .toList();
            }

            return client.getUserLibrary(String.valueOf(userId), kind)
                    .flatMapObservable(new Function<LibraryResponse, ObservableSource<List<LibraryData>>>() {
                        @Override
                        public ObservableSource<List<LibraryData>> apply(LibraryResponse libraryResponse) {
                            return Observable.just(libraryResponse.getData());
                        }
                    })
                    .flatMapIterable(new Function<List<LibraryData>, Iterable<LibraryData>>() {
                        @Override
                        public Iterable<LibraryData> apply(List<LibraryData> libraryData) {
                            return libraryData;
                        }
                    })
                    .flatMap(new Function<LibraryData, ObservableSource<AnimeResponse>>() {
                        @Override
                        public ObservableSource<AnimeResponse> apply(LibraryData libraryData) {
                            return client.getAnimeById(libraryData.getId()).toObservable();
                        }
                    }, new BiFunction<LibraryData, AnimeResponse, BaseSeries>() {
                        @Override
                        public BaseSeries apply(LibraryData libraryData, AnimeResponse animeResponse) {
                            return DataUtils.convertAnimeFromRemotetoLocal(libraryData, animeResponse.getData());
                        }
                    })
                    .toList();
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
