package com.easyapp.lib.drawer;


import android.content.Context;

import androidx.appcompat.app.ActionBarDrawerToggle;

import com.easyapp.lib.fragment.BaseToolbarFragment;
import com.easyapp.lib.callback.iDrawerCallback;

/**
 * 基本側邊欄fragment
 */
public abstract class BaseDrawerFragment extends BaseToolbarFragment {

    protected iDrawerCallback drawerCallback;

    protected void setDrawerCallback(Context context) {
        if (context instanceof BaseDrawerMainActivity) {
            try {
                drawerCallback = (iDrawerCallback) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            setDrawerCallback(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ActionBarDrawerToggle getDrawerToggle() {
        return drawerCallback.getDrawerToggle();
    }

    protected void setDrawerLock() {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.setDrawerLock();
    }

    protected void setDrawerUnLock() {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.setDrawerUnLock();
    }

    protected void openDrawer(int gravity) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.openDrawer(gravity);
    }

    protected void closeDrawer(int gravity) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.closeDrawer(gravity);
    }
}
