package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.callback.iDrawerCallback;

/**
 * 基本的側邊欄activity
 */
public abstract class BaseDrawerMainActivity extends BaseMainActivity implements iDrawerCallback {

    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.easyapp_base_drawer_main);
        setContainer(R.id.container);
        initView();
        initDrawer();
        initial();
    }


    protected void initDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    protected abstract void initial();

    @Override
    public void addLeftDrawer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_left, fragment, "left").commitAllowingStateLoss();
    }

    @Override
    public void addRightDrawer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_left, fragment, "left").commitAllowingStateLoss();
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
