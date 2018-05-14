package com.udacity.animal.data.network;

import com.udacity.animal.data.entities.entries.AnimeResponse;
import com.udacity.animal.data.entities.entries.MangaResponse;
import com.udacity.animal.data.entities.user.AnimeListResponse;
import com.udacity.animal.data.entities.user.MangaListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MALService {


    @GET("api/account/verify_credentials.xml")
    Call<Void> verfiyCredentials();

    @GET("/api/anime/search.xml")
    Call<AnimeResponse> searchAllAnime(
            @Query("q") String name);

    @GET("malappinfo.php")
    Call<AnimeListResponse> getUserAnimes(
            @Query("u") String username,
            @Query("type") String type);

    @POST("/api/animelist/add/{id}")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<POST> addAnime(
            @Path("id") String id,
            @Body String data);

    @POST("/api/animelist/update/{id}")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<POST> updateAnime(
            @Path("id") String id,
            @Body String data);

    @POST("/api/animelist/delete/{id}")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    Call<POST> removeAnime(
            @Path("id") String id);

    @GET("/api/manga/search.xml")
    Call<MangaResponse> searchAllManga(
            @Query("q") String name);

    @GET("malappinfo.php")
    Call<MangaListResponse> getUserMangas(
            @Query("u") String username,
            @Query("type") String type);

    @POST("/api/mangalist/add/{id}")
    @Headers({"Content-Type: aapplication/x-www-form-urlencoded"})
    Call<POST> addManga(
            @Path("id") String id,
            @Body String data);

    @POST("/api/mangalist/update/{id}")
    @Headers({"Content-Type: aapplication/x-www-form-urlencoded"})
    Call<POST> updateManga(
            @Path("id") String id,
            @Body String data);

    @POST("/api/mangalist/delete/{id}")
    @Headers({"Content-Type: aapplication/x-www-form-urlencoded"})
    Call<POST> removeManga(
            @Path("id") String id);
}
