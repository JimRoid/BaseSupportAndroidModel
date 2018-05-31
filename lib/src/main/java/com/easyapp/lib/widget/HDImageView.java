package com.easyapp.lib.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

public class HDImageView extends AppCompatImageView {

    private static final float RATIO = 16.f / 9;

    public HDImageView(Context context) {
        super(context);
    }

    public HDImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HDImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(parentWidth, (int) (parentWidth / RATIO));
    }

}