package com.easyapp.image.view;

import android.content.Context;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * An image view which always remains square with respect to its width.
 */
public class SquaredImageView extends AppCompatImageView {


    public SquaredImageView(Context context) {
        super(context);
    }

    public SquaredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaredImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
