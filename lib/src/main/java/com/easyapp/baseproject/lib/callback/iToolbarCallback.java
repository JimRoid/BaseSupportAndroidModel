package com.easyapp.baseproject.lib.callback;

import android.view.View;

/**
 * toolbar callback 回傳
 */
public interface iToolbarCallback {

    void setLeft(View view);

    void setRight(View view);

    void setLeft(View view[]);

    void setRight(View view[]);

    void cancelRight();

    void cancelLeft();

    void showBack(boolean value);

    void hideToolbar();

    void showToolbar();

    void setTitle(CharSequence sequence);

    void setTitle(int title_id);
}