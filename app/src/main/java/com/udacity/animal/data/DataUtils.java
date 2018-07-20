package com.udacity.animal.data;

import com.udacity.animal.data.entities.local.AnimeSeries;
import com.udacity.animal.data.entities.local.BaseSeries;
import com.udacity.animal.data.entities.local.MangaSeries;
import com.udacity.animal.data.entities.local.User;
import com.udacity.animal.data.entities.remote.serie.AnimeAttributes;
import com.udacity.animal.data.entities.remote.serie.AnimeData;
import com.udacity.animal.data.entities.remote.serie.MangaAttributes;
import com.udacity.animal.data.entities.remote.serie.MangaData;
import com.udacity.animal.data.entities.remote.serie.SeriesAttributes;
import com.udacity.animal.data.entities.remote.serie.Titles;
import com.udacity.animal.data.entities.remote.user.UserAttributes;
import com.udacity.animal.data.entities.remote.user.UserData;
import com.udacity.animal.data.entities.remote.userlibrary.LibraryAttributes;
import com.udacity.animal.data.entities.remote.userlibrary.LibraryData;
import com.udacity.animal.data.entities.remote.userlibrary.LibraryMeta;
import com.udacity.animal.data.entities.remote.userlibrary.StatusCounts;
import com.udacity.animal.util.Utils;

import java.util.Calendar;

public class DataUtils {

    private static final String ANIME = "anime";
    private static final String MANGA = "manga";
    private static final String START_END_DATE_PATTERN = "yyyy-MM-dd";
    private static final String PROGRESS_FINISH_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    static AnimeSeries convertAnimeFromRemotetoLocal(LibraryData libraryData, AnimeData animeData) {
        if (libraryData == null || animeData == null) {
            return null;
        }

        LibraryAttributes libraryAttributes = libraryData.getAttributes();
        AnimeAttributes animeAttributes = animeData.getAttributes();
        AnimeSeries local = (AnimeSeries) convertSeriesFromRemoteToLocal(libraryAttributes, animeAttributes, true);

        local.setId(Long.valueOf(animeData.getId()));
        local.setType(ANIME);
        local.setEpisodeCount(animeAttributes.getEpisodeCount());
        local.setEpisodeLength(animeAttributes.getEpisodeLength());
        local.setYoutubeVideoId(animeAttributes.getYoutubeVideoId());

        return local;
    }

    static MangaSeries convertMangaFromRemoteToLocal(LibraryData libraryData, MangaData mangaData) {
        if (libraryData == null || mangaData == null) {
            return null;
        }

        LibraryAttributes libraryAttributes = libraryData.getAttributes();
        MangaAttributes mangaAttributes = mangaData.getAttributes();
        MangaSeries local = (MangaSeries) convertSeriesFromRemoteToLocal(libraryAttributes, mangaAttributes, false);

        local.setId(Long.valueOf(mangaData.getId()));
        local.setType(MANGA);
        local.setChapterCount(mangaAttributes.getChapterCount());
        local.setVolumeCount(mangaAttributes.getVolumeCount());
        local.setSerialization(mangaAttributes.getSerialization());

        return local;
    }

    private static BaseSeries convertSeriesFromRemoteToLocal(LibraryAttributes libraryAttributes,
                                                     SeriesAttributes seriesAttributes, boolean isAnime) {
        BaseSeries local;
        if (isAnime) {
            local = new AnimeSeries();
        } else {
            local = new MangaSeries();
        }

        local.setSynopsis(seriesAttributes.getSynopsis());
        if (seriesAttributes.getTitles() != null) {
            Titles titles = seriesAttributes.getTitles();
            local.setTitleEnglish(titles.getEn());
            local.setTitleJapanese(titles.getJaJp());
            local.setTitleRomanji(titles.getEnJp());
        }
        local.setCanonicalTitle(seriesAttributes.getCanonicalTitle());
        local.setAverageRating(Double.valueOf(seriesAttributes.getAverageRating()));
        Calendar startCal = Utils.getCalendarFromString(seriesAttributes.getStartDate(), START_END_DATE_PATTERN);
        if (startCal != null) {
            local.setStartDate(startCal);
        }
        Calendar endCal = Utils.getCalendarFromString(seriesAttributes.getEndDate(), START_END_DATE_PATTERN);
        if (endCal != null) {
            local.setEndDate(endCal);
        }
        local.setSubtype(seriesAttributes.getSubtype());
        local.setStatus(seriesAttributes.getStatus());
        local.setPosterImage(seriesAttributes.getPosterImage());
        local.setCoverImage(seriesAttributes.getCoverImage());
        local.setUserStatus(libraryAttributes.getStatus());
        local.setProgress(libraryAttributes.getProgress());
        local.setNotes(libraryAttributes.getNotes());
        Calendar progressedAtCal = Utils.getCalendarFromString(libraryAttributes.getProgressedAt(), PROGRESS_FINISH_PATTERN);
        if (progressedAtCal != null) {
            local.setProgressedAt(progressedAtCal);
        }
        Calendar startedAtCal = Utils.getCalendarFromString(libraryAttributes.getStartedAt(), PROGRESS_FINISH_PATTERN);
        if (startedAtCal != null) {
            local.setStartedAt(startedAtCal);
        }
        Calendar finishedAtCal = Utils.getCalendarFromString(libraryAttributes.getFinishedAt(), PROGRESS_FINISH_PATTERN);
        if (finishedAtCal != null) {
            local.setFinishedAt(finishedAtCal);
        }
        local.setRatingTwenty(libraryAttributes.getRatingTwenty());

        return local;
    }

    public static User convertUserFromRemoteToLocal(UserData data, LibraryMeta meta,
                                                    String username, String password) {

        User user = new User();
        user.setId(Long.valueOf(data.getId()));
        user.setUsername(username);
        user.setPassword(password);

        UserAttributes attributes = data.getAttributes();

        user.setName(attributes.getName());
        user.setAvatar(attributes.getAvatar());
        user.setCoverImage(attributes.getCoverImage());
        user = addMetaToUser(user, meta);

        return user;
    }

    public static User addMetaToUser(User user, LibraryMeta meta) {
        if (meta != null) {
            StatusCounts statusCounts = meta.getStatusCounts();
            if (statusCounts != null) {
                user.setCurrent(statusCounts.getCurrent());
                user.setCompleted(statusCounts.getCompleted());
                user.setDropped(statusCounts.getDropped());
                user.setOnHold(statusCounts.getOnHold());
                user.setPlanned(statusCounts.getPlanned());
            }
        }

        return user;
    }
}
