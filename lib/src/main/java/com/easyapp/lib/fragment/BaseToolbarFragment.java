package com.easyapp.lib.fragment;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

    protected void cancelRight() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.cancelRight();
    }

    protected void cancelLeft() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.cancelLeft();
    }

    protected void setLeft(View view) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setLeft(view);
    }


    protected void setRight(View view) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setRight(view);
    }


    protected void setLeft(View[] views) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setLeft(views);
    }

    protected void setRight(View[] views) {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.setRight(views);
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
}
