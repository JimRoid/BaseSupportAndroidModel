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


    public void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }


    public void setTitle(int titleId) {
        getActivity().setTitle(titleId);
    }

    public void showBack(boolean value) {
        toolbar_callback.showBack(value);
    }

    public void cancelRight() {
        toolbar_callback.cancelRight();
    }

    public void cancelLeft() {
        toolbar_callback.cancelLeft();
    }

    public void setLeft(View view) {
        toolbar_callback.setLeft(view);
    }


    public void setRight(View view) {
        toolbar_callback.setRight(view);
    }


    public void setLeft(View[] views) {
        toolbar_callback.setLeft(views);
    }

    public void setRight(View[] views) {
        toolbar_callback.setRight(views);
    }
}
