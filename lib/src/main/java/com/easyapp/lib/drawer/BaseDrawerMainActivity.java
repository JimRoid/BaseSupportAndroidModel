package com.easyapp.lib.drawer;


import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.easyapp.lib.R;
import com.easyapp.lib.activity.BaseMainActivity;
import com.easyapp.lib.callback.iDrawerCallback;

/**
 * 基本的側邊欄activity
 */
public abstract class BaseDrawerMainActivity extends BaseMainActivity implements iDrawerCallback {

    protected DrawerLayout drawerLayout;
    protected NavigationView navView;
    protected ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_drawer;
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showBack(boolean value) {
        if (value) {
            getDrawerToggle().setDrawerIndicatorEnabled(false);
        } else {
            getDrawerToggle().setDrawerIndicatorEnabled(true);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void ReplaceFragment(Fragment fragment) {
        super.ReplaceFragment(fragment);
        showBack(false);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, String anim) {
        super.ReplaceFragment(fragment, anim);
        showBack(false);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, int container) {
        super.ReplaceFragment(fragment, container);
        showBack(false);
    }

    @Override
    public void ReplaceFragment(Fragment fragment, int container, String anim) {
        super.ReplaceFragment(fragment, container, anim);
        showBack(false);
    }


    @Override
    public ActionBarDrawerToggle getDrawerToggle() {
        return mDrawerToggle;
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
