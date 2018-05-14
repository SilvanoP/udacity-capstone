package com.udacity.animal.utils;

import android.content.Context;

public class Utils {

    public int getResourceIdByIdentifier(Context context, String identifier) {
        return context.getResources().getIdentifier(identifier, "string",
                context.getPackageName());
    }
}
