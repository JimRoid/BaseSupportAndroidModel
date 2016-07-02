package com.easyapp.baseproject.lib.base.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.easyapp.baseproject.lib.callback.iDrawerCallback;

/**
 * 基本側邊欄fragment
 */
public abstract class BaseDrawerFragment extends BaseToolbarFragment {

    private iDrawerCallback drawerCallback;

    protected void setDrawerCallback(Context context){
        try {
            drawerCallback = (iDrawerCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    protected void addLeftDrawer(Fragment fragment) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.addLeftDrawer(fragment);

    }

    protected void addRightDrawer(Fragment fragment) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.addRightDrawer(fragment);
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
