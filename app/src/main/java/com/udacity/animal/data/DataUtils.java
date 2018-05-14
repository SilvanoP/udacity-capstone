package com.udacity.animal.data;

import com.udacity.animal.data.entities.entries.AnimeEntry;
import com.udacity.animal.data.entities.entries.BaseEntry;
import com.udacity.animal.data.entities.entries.MangaEntry;
import com.udacity.animal.data.entities.user.BaseUserItem;
import com.udacity.animal.data.entities.user.UserAnimeItem;
import com.udacity.animal.data.entities.user.UserMangaItem;

public class DataUtils {

    public static BaseUserItem convertEntryToUserItem(BaseEntry entry) {
        if (entry instanceof MangaEntry)
            return convertMangaEntryToMangaItem((MangaEntry) entry);
        else
            return convertAnimeEntryToAnimeItem((AnimeEntry) entry);
    }

    private static UserMangaItem convertMangaEntryToMangaItem(MangaEntry entry) {
        // TODO  search on database if the manga id already exists and get user info from there

        UserMangaItem item = new UserMangaItem();
        item.setId(entry.getId());
        item.setTitle(entry.getTitle());
        item.setType(entry.getType());
        item.setStatus(entry.getStatus());
        item.setImageUrl(entry.getImageUrl());
        item.setSeriesStartDate(entry.getStartDate());
        item.setSeriesEndDate(entry.getEndDate());
        item.setTotalChapters(entry.getChapters());
        item.setVolumes(entry.getVolumes());

        return item;
    }

    private static UserAnimeItem convertAnimeEntryToAnimeItem(AnimeEntry entry) {
        // TODO  search on database if the anime id already exists and get user info from there

        UserAnimeItem item = new UserAnimeItem();
        item.setId(entry.getId());
        item.setTitle(entry.getTitle());
        item.setType(entry.getType());
        item.setStatus(entry.getStatus());
        item.setImageUrl(entry.getImageUrl());
        item.setSeriesStartDate(entry.getStartDate());
        item.setSeriesEndDate(entry.getEndDate());
        item.setTotalEpisodes(entry.getEpisodes());

        return item;
    }
}
