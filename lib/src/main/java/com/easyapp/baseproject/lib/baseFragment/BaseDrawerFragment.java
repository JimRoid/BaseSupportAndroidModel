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
        try {
            drawerCallback = (iDrawerCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    public void addLeftDrawer(Fragment fragment) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.addLeftDrawer(fragment);

    }

    public void addRightDrawer(Fragment fragment) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.addRightDrawer(fragment);
    }

    public void setDrawerLock() {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.setDrawerLock();
    }

    public void setDrawerUnLock() {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.setDrawerUnLock();
    }

    public void openDrawer(int gravity) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.openDrawer(gravity);
    }

    public void closeDrawer(int gravity) {
        if (drawerCallback == null) {
            return;
        }
        drawerCallback.closeDrawer(gravity);
    }
}
