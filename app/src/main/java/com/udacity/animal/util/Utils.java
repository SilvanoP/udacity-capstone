package com.udacity.animal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static Calendar getCalendarFromString(String source, String pattern) {
        if (TextUtils.isEmpty(source) || TextUtils.isEmpty(pattern)) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
            cal.setTime(format.parse(source));
        } catch (ParseException e) {
            return null;
        }


        return cal;
    }

    public static String getStringFromCalendar(Calendar cal, String pattern) {
        if (cal == null || TextUtils.isEmpty(pattern)) {
            return "";
        }

        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
}
