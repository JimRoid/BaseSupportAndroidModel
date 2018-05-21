package com.easyapp.lib.fragment;

import android.content.Context;
import android.support.v7.widget.Toolbar;
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
