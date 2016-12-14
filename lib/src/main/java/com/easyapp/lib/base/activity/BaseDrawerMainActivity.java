package com.easyapp.lib.base.activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.easyapp.lib.R;
import com.easyapp.lib.callback.iDrawerCallback;

/**
 * 基本的側邊欄activity
 */
public abstract class BaseDrawerMainActivity extends BaseMainActivity implements iDrawerCallback {

    protected DrawerLayout drawerLayout;

    protected View drawerRight;
    protected View drawerLeft;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_drawer;
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLeft = findViewById(R.id.fl_drawer_left);
        drawerRight = findViewById(R.id.fl_drawer_right);
    }

    /**
     * 設定drawer 背景
     *
     * @param res
     */
    public void setDrawerLayoutBackground(int res) {
        drawerRight.setBackgroundResource(res);
        drawerLeft.setBackgroundResource(res);
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
