package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class ImageUtils {
    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return Math.round(px);
    }
}
