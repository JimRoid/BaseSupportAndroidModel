package com.easyapp.lib.callback;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

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

    void clearRightMenu();

    void clearLeftMenu();

    void clearMenu();

    ViewGroup getLeftMenu();

    ViewGroup getRightMenu();
}