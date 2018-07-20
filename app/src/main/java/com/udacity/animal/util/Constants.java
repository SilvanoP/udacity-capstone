package com.udacity.animal.util;

import com.udacity.animal.BuildConfig;

public final class Constants {

    public static final String BASE_URL = "https://kitsu.io/api/";
    public static final String CLIENT_ID = BuildConfig.API_ID;
    public static final String CLIENT_SECRET = BuildConfig.API_SECRET;

    public static final String IS_ANIME_EXTRA = "IS_ANIME";
    public static final String USER_EXTRA = "USER";

    // This class uses the status as taken from Kitsu API docs status field
    public final class UserStatus {
        public static final String COMPLETED = "completed";
        public static final String CURRENT = "current";
        public static final String DROPPED = "dropped";
        public static final String ON_HOLD = "on_hold";
        public static final String PLANNED = "planned";
    }

    public final class DataSource {
        public static final String REMOTE = "remote";
        public static final String LOCAL = "local";
    }

}
