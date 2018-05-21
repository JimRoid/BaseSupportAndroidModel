package com.easyapp.lib.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.lib.callback.iFloatingActionButton;
import com.easyapp.lib.callback.iToolbarCallback;

/**
 * 基本toolbar fragment
 */
public class BaseToolbarFragment extends BaseEasyFragment {

    protected iToolbarCallback toolbarCallback;
    protected iFloatingActionButton floatingActionButton;

    protected void setToolbarCallback(Context context) {
        try {
            toolbarCallback = (iToolbarCallback) context;
            floatingActionButton = (iFloatingActionButton) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    protected void autoHideToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) getToolbar().getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
    }

    protected void cancelHideToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) getToolbar().getLayoutParams();
        params.setScrollFlags(0);
    }

    protected FloatingActionButton getFab() {
        if (floatingActionButton == null) {
            return null;
        }
        return floatingActionButton.getFab();
    }

    protected void showFab() {
        if (floatingActionButton == null) {
            return;
        }
        floatingActionButton.showFab();
    }

    protected void hideFab() {
        if (floatingActionButton == null) {
            return;
        }
        floatingActionButton.hideFab();
    }

    protected Toolbar getToolbar() {
        if (toolbarCallback == null) {
            return null;
        }
        return toolbarCallback.getToolbar();
    }

    protected void setTitle(CharSequence title) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setTitle(title);
    }

    protected void setTitle(int titleId) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setTitle(titleId);
    }

    protected void setTitleImageResource(int titleId) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setTitleImageResource(titleId);
    }

    protected void showBack(boolean value) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.showBack(value);
    }

    protected void showToolbar() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.showToolbar();
    }

    protected void hideToolbar() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.hideToolbar();
    }


    public void clearRightMenu() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.clearRightMenu();
    }

    public void clearLeftMenu() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.clearLeftMenu();
    }

    public void clearMenu() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.clearMenu();
    }

    public ViewGroup getLeftMenu() {
        if (toolbarCallback == null) {
            return null;
        }
        return toolbarCallback.getLeftMenu();
    }


    public ViewGroup getRightMenu() {
        if (toolbarCallback == null) {
            return null;
        }
        return toolbarCallback.getRightMenu();
    }
}
