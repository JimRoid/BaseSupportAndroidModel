package com.easyapp.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.orhanobut.logger.Logger;

public class HDFrameLayout extends FrameLayout {

    // 宽高比
    private static final float RATIO = 16.f / 9;

    public HDFrameLayout(Context context) {
        super(context);
    }

    public HDFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HDFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int childWidth = parentWidth - getPaddingLeft() - getPaddingRight();
            int childHeight = (int) (childWidth / RATIO + 0.5f);
            int parentHeight = childHeight + getPaddingBottom() + getPaddingTop();
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
            setMeasuredDimension(parentWidth, parentHeight);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}