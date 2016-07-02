package com.easyapp.baseproject.lib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.orhanobut.logger.Logger;

/**
 * 水平方向的scroll view
 * 可在滑到底的時候呼叫
 */
public class EasyHorizontalScrollView extends HorizontalScrollView {
    OnBottomReachedListener mListener;

    public EasyHorizontalScrollView(Context context) {
        super(context);
    }

    public EasyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount() - 1);
        int diff = (view.getRight() - (getWidth() + getScrollX()));
        if (diff == 0 && mListener != null) {
            Logger.d("right");
            mListener.onBottomReached();
        } else {
            mListener.onStartReached();
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }

    // Getters & Setters
    public OnBottomReachedListener getOnBottomReachedListener() {
        return mListener;
    }

    public void setOnBottomReachedListener(
            OnBottomReachedListener onBottomReachedListener) {
        mListener = onBottomReachedListener;
    }


    /**
     * Event listener.
     */
    public interface OnBottomReachedListener {
        void onBottomReached();

        void onStartReached();
    }
}
