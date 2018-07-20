package com.udacity.animal.data.services;

import com.udacity.animal.data.entities.AccessToken;
import com.udacity.animal.data.entities.remote.serie.AnimeResponse;
import com.udacity.animal.data.entities.remote.serie.MangaResponse;
import com.udacity.animal.data.entities.remote.user.UserResponse;
import com.udacity.animal.data.entities.remote.userlibrary.LibraryResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KitsuClient {

    @POST("oauth/token")
    Single<AccessToken> getToken(
            @Query("grant_type") String grantType,
            @Query("username") String username,
            @Query("password") String password,
            @Query("client_id") String id,
            @Query("client_secret") String secret
    );

    @GET("edge/users")
    Single<UserResponse> getUser(
            @Query("filter[name]") String username
    );

    @GET("edge/trending/anime")
    Single<AnimeResponse> getTrendingAnime();

    @GET("edge/trending/manga")
    Single<MangaResponse> getTrendingManga();

    @GET("edge/library-entries/{id}/anime")
    Single<AnimeResponse> getAnimeById(
            @Path("id") String animeId
    );

    @GET("edge/library-entries/{id}/manga")
    Single<MangaResponse> getMangaById(
            @Path("id") String mangaId
    );

    @GET("edge/users/{user_id}/library-entries")
    Single<LibraryResponse> getUserLibrary(
            @Path("user_id") String userId,
            @Query("filter[kind]") String kind
    );

    @GET("edge/users/{user_id}/library-entries")
    Single<LibraryResponse> getUserLibraryAnime(
            @Path("user_id") String userId,
            @Query("filter[animeId]") String kind
    );

    @GET("edge/users/{user_id}/library-entries?filter[kind]=anime")
    Single<LibraryResponse> getUserAnime(
            @Path("user_id") String userId
    );

    @GET("edge/users/{user_id}/library-entries?filter[kind]=manga")
    Single<LibraryResponse> getUserManga(
            @Path("user_id") String userId
    );
}
