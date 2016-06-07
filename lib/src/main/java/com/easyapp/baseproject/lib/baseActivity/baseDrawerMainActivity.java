package com.easyapp.baseproject.lib.baseActivity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.callback.iDrawerCallback;

/**
 * 基本的側邊欄activity
 */
public abstract class BaseDrawerMainActivity extends BaseMainActivity implements iDrawerCallback {

    protected DrawerLayout drawerLayout;

    private View fl_drawer_right;
    private View fl_drawer_left;

    @Override
    protected int getLayoutId() {
        return R.layout.easyapp_activity_base_drawer;
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fl_drawer_left = findViewById(R.id.fl_drawer_left);
        fl_drawer_right = findViewById(R.id.fl_drawer_right);
    }

    /**
     * 設定drawer 背景
     *
     * @param res
     */
    public void setDrawerLayoutBackground(int res) {
        fl_drawer_right.setBackgroundResource(res);
        fl_drawer_left.setBackgroundResource(res);
    }

    @Override
    public void addLeftDrawer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_left, fragment, "left").commitAllowingStateLoss();
    }

    @Override
    public void addRightDrawer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_right, fragment, "left").commitAllowingStateLoss();
    }

    @Override
    public void setDrawerLock() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void setDrawerUnLock() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void setDrawerState(int state) {
        drawerLayout.setDrawerLockMode(state);
    }

    @Override
    public void openDrawer(int gravity) {
        drawerLayout.openDrawer(gravity);
    }

    @Override
    public void closeDrawer(int gravity) {
        drawerLayout.closeDrawer(gravity);
    }
}
