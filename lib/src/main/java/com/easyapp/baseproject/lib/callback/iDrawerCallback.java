package com.easyapp.baseproject.lib.callback;

import android.support.v4.app.Fragment;

/**
 * Created by easyapp_jim on 2016/4/14.
 */
public interface iDrawerCallback {
    void openDrawer(int gravity);

    void closeDrawer(int gravity);

    void addLeftDrawer(Fragment fragment);

    void addRightDrawer(Fragment fragment);

    void setDrawerLock();

    void setDrawerUnLock();

    void setDrawerState(int state);
}
