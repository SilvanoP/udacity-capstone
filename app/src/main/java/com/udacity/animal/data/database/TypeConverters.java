package com.udacity.animal.data.database;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import com.udacity.animal.util.Utils;

import java.util.Calendar;

public class TypeConverters {

    private static final String START_END_DATE_PATTERN = "yyyy-MM-dd";
    private static final String PROGRESS_FINISH_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @TypeConverter
    public static Calendar fromString(String value) {
        String pattern = START_END_DATE_PATTERN;
        if (!TextUtils.isEmpty(value) && value.contains("T")) {
            pattern = PROGRESS_FINISH_PATTERN;
        }
        return Utils.getCalendarFromString(value, pattern);
    }

    @TypeConverter
    public static String fromCalendar(Calendar calendar) {
        return Utils.getStringFromCalendar(calendar, START_END_DATE_PATTERN);
    }
}
