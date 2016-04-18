package com.easyapp.baseproject.lib.baseFragment;

import android.content.Context;
import android.view.View;

import com.easyapp.baseproject.lib.callback.iToolbarCallback;

/**
 * Created by easyapp_jim on 2016/4/13.
 */
public class BaseToolbarFragment extends BaseEasyFragment {

    protected iToolbarCallback toolbar_callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            toolbar_callback = (iToolbarCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    protected void hideToolbar() {
        toolbar_callback.hideToolbar();
    }

    protected void showToolbar() {
        toolbar_callback.showToolbar();
    }


    protected void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }


    protected void setTitle(int titleId) {
        getActivity().setTitle(titleId);
    }

    protected void showBack(boolean value) {
        toolbar_callback.showBack(value);
    }

    protected void cancelRight() {
        toolbar_callback.cancelRight();
    }

    protected void cancelLeft() {
        toolbar_callback.cancelLeft();
    }

    protected void setLeft(View view) {
        toolbar_callback.setLeft(view);
    }


    protected void setRight(View view) {
        toolbar_callback.setRight(view);
    }


    protected void setLeft(View[] views) {
        toolbar_callback.setLeft(views);
    }

    protected void setRight(View[] views) {
        toolbar_callback.setRight(views);
    }
}
