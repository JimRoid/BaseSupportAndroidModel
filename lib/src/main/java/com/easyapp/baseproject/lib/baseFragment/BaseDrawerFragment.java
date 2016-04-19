package com.easyapp.baseproject.lib.baseFragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.easyapp.baseproject.lib.callback.iDrawerCallback;

/**
 * 基本側邊欄fragment
 */
public abstract class BaseDrawerFragment extends BaseToolbarFragment {

    private iDrawerCallback drawerCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        drawerCallback = (iDrawerCallback) context;
    }

    public void addLeftDrawer(Fragment fragment) {
        drawerCallback.addLeftDrawer(fragment);
    }

    public void addRightDrawer(Fragment fragment) {
        drawerCallback.addRightDrawer(fragment);
    }

    public void setDrawerLock() {
        drawerCallback.setDrawerLock();
    }

    public void setDrawerUnLock() {
        drawerCallback.setDrawerUnLock();
    }

    public void openDrawer(int gravity) {
        drawerCallback.openDrawer(gravity);
    }

    public void closeDrawer(int gravity) {
        drawerCallback.closeDrawer(gravity);
    }
}
