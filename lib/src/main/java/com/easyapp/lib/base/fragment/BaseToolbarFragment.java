package com.easyapp.lib.base.fragment;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.easyapp.lib.callback.iToolbarCallback;

/**
 * 基本toolbar fragment
 */
public class BaseToolbarFragment extends BaseEasyFragment {

    protected iToolbarCallback toolbarCallback;

    protected void setToolbarCallback(Context context) {
        try {
            toolbarCallback = (iToolbarCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    protected void showFab() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.showFab();
    }

    protected void hideFab() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.hideFab();
    }

    protected FloatingActionButton getFab() {
        if (toolbarCallback == null) {
            return null;
        }
        return toolbarCallback.getFab();
    }

    protected void hideToolbar() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.hideToolbar();
    }

    protected void showToolbar() {
        if (toolbarCallback == null) {
            return;
        }
        toolbarCallback.showToolbar();
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
}
