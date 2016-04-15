package com.easyapp.baseproject.lib.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.easyapp.baseproject.lib.R;

/**
 * Created by easyapp_jim on 2016/4/14.
 */
public class Menu extends TextView {

    public Menu(Context context) {
        this(context, null);
    }

    public Menu(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public Menu(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Menu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return Menu.class.getName();
    }
}
