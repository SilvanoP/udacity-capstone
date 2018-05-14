package com.udacity.animal.presenter;

import com.udacity.animal.data.entities.user.BaseUserItem;
import com.udacity.animal.data.entities.user.UserAnimeItem;
import com.udacity.animal.data.entities.user.UserMangaItem;

import java.util.List;

public interface ListPresenter {

    void listUserItemByStatus(String username, Status status, Type type);
    void searchUserItem(String title, Type type);

    interface Callback {
        void onReceiveUserAnimeItems(List<UserAnimeItem> items);
        void onReceiveUserMangaItems(List<UserMangaItem> items);
        void onError(String message);
    }
}
