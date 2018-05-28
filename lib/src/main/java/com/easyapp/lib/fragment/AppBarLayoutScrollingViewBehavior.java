package com.easyapp.lib.fragment;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

public class AppBarLayoutScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

    private AppBarLayout appBarLayout;
    private String TAG = "AppBarLayoutScrollingViewBehavior";

    public AppBarLayoutScrollingViewBehavior() {
        super();
    }

    public AppBarLayoutScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        if (appBarLayout == null) {
            appBarLayout = (AppBarLayout) dependency;
        }

        final boolean result = super.onDependentViewChanged(parent, child, dependency);
        final int bottomPadding = calculateBottomPadding(appBarLayout);
        final boolean paddingChanged = bottomPadding != child.getPaddingBottom();
        Logger.d("child.getPaddingBottom():" + child.getPaddingBottom());
        Logger.d("bottomPadding:" + bottomPadding);
        Logger.d("paddingChanged:" + paddingChanged);

        if (paddingChanged) {
            child.setPadding(
                    child.getPaddingLeft(),
                    child.getPaddingTop(),
                    child.getPaddingRight(),
                    bottomPadding);
            child.requestLayout();
        }
        Logger.d("paddingChanged:" + paddingChanged);
        Logger.d("result:" + result);
        Logger.d("paddingChanged || result:" + (paddingChanged || result));
        return paddingChanged || result;
    }


    // Calculate the padding needed to keep the bottom of the view pager's content at the same location on the screen.
    private int calculateBottomPadding(AppBarLayout dependency) {
        Logger.d("dependency.getTotalScrollRange():" + dependency.getTotalScrollRange());
        Logger.d("dependency.getTop():" + dependency.getTop());
        final int totalScrollRange = dependency.getTotalScrollRange();
        return totalScrollRange + dependency.getTop();
    }
}