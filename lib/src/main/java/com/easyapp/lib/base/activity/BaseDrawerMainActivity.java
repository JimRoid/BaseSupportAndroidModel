package com.easyapp.lib.base.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
    protected ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_drawer;
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLeft = findViewById(R.id.fl_drawer_left);
        drawerRight = findViewById(R.id.fl_drawer_right);


        mDrawerToggle = new ActionBarDrawerToggle(
                this,              /* host Activity */
                drawerLayout,                    /* DrawerLayout object */
                // ((BaseActivity) getActivityCompat()).getToolbar(), <== delete this argument
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        closeDrawer(GravityCompat.START);
                    } else {
                        openDrawer(GravityCompat.START);
                    }
                } else {
                    onBackPressed();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void showBack(boolean value) {
        if (!value) {
            getDrawerToggle().setDrawerIndicatorEnabled(true);
        } else {
            getDrawerToggle().setDrawerIndicatorEnabled(false);
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
    public ActionBarDrawerToggle getDrawerToggle() {
        return mDrawerToggle;
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
