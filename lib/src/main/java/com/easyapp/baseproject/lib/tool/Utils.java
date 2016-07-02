package com.easyapp.baseproject.lib.tool;

import android.content.Context;
import android.content.res.TypedArray;

import com.easyapp.baseproject.lib.R;

public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getDimensionHeight(Context context, int dimen) {
        return (int) context.getResources().getDimension(dimen);
    }
}