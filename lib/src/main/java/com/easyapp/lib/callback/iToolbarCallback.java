package com.easyapp.lib.callback;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

/**
 * toolbar callback 回傳
 */
public interface iToolbarCallback {

    void showBack(boolean value);

    void setTitle(CharSequence sequence);

    void setTitle(int title_id);

    void setTitleImageResource(int resId);

    void showToolbar();

    void hideToolbar();

    Toolbar getToolbar();

    AppBarLayout getAppBarLayout();

    void clearRightMenu();

    void clearLeftMenu();

    void clearMenu();

    ViewGroup getLeftMenu();

    ViewGroup getRightMenu();
}