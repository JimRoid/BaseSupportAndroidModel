package com.easyapp.lib.fragment;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.easyapp.lib.callback.iToolbarCallback;

/**
 * 基本toolbar fragment
 */
public abstract class BaseToolbarFragment extends BaseEasyFragment {

    protected iToolbarCallback toolbarCallback;

    protected void setToolbarCallback(Context context) {
        try {
            toolbarCallback = (iToolbarCallback) context;
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

    @Override
    public boolean onBackPressed() {
        return false;
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

    public AppBarLayout getAppBarLayout() {
        if (toolbarCallback == null) {
            return null;
        }
        return toolbarCallback.getAppBarLayout();
    }
}
