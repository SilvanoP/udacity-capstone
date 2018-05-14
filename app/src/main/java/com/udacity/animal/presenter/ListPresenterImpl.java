package com.udacity.animal.presenter;

import com.udacity.animal.data.entities.user.AnimeListResponse;
import com.udacity.animal.data.entities.user.MangaListResponse;
import com.udacity.animal.data.entities.user.UserAnimeItem;
import com.udacity.animal.data.entities.user.UserMangaItem;
import com.udacity.animal.data.network.MALService;
import com.udacity.animal.data.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ListPresenterImpl implements ListPresenter {

    private ListPresenter.Callback mCallback;
    private MALService service;

    public ListPresenterImpl(Callback mCallback) {
        this.mCallback = mCallback;
        service = ServiceGenerator.INSTANCE().getService();
    }

    @Override
    public void listUserItemByStatus(String username, Status status, Type type) {
        if (type.equals(Type.ANIME)) {
            Call<AnimeListResponse> call = service.getUserAnimes(username, type.getValue());
            call.enqueue(new retrofit2.Callback<AnimeListResponse>() {
                @Override
                public void onResponse(Call<AnimeListResponse> call, Response<AnimeListResponse> response) {
                    AnimeListResponse list = response.body();
                    if (list != null) {
                        List<UserAnimeItem> animes = list.getUserAnimeItens();
                        mCallback.onReceiveUserAnimeItems(animes);
                    }

                    mCallback.onError(response.message());
                }

                @Override
                public void onFailure(Call<AnimeListResponse> call, Throwable t) {
                    mCallback.onError(t.getMessage());
                }
            });
        } else if (type.equals(Type.MANGA)) {
            Call<MangaListResponse> call = service.getUserMangas(username, type.getValue());
            call.enqueue(new retrofit2.Callback<MangaListResponse>() {
                @Override
                public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                    MangaListResponse list = response.body();
                    if (list != null) {
                        List<UserMangaItem> mangas = list.getUserMangaItens();
                        mCallback.onReceiveUserMangaItems(mangas);
                    }

                    mCallback.onError(response.message());
                }

                @Override
                public void onFailure(Call<MangaListResponse> call, Throwable t) {
                    mCallback.onError(t.getMessage());
                }
            });
        }
    }

    @Override
    public void searchUserItem(String title, Type type) {

    }
}
