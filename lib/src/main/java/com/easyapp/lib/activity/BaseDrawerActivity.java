package com.easyapp.lib.activity;

import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.easyapp.lib.R;
import com.easyapp.lib.activity.BaseToolbarActivity;
import com.easyapp.lib.callback.iDrawerCallback;
import com.google.android.material.navigation.NavigationView;
import com.orhanobut.logger.Logger;

/**
 * 基本的側邊欄activity
 */
public abstract class BaseDrawerActivity extends BaseToolbarActivity implements iDrawerCallback {

    protected DrawerLayout drawerLayout;
    protected NavigationView navView;
    protected ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_base_drawer;
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragNavController.isRootFragment()) {
                    openDrawer(GravityCompat.START);
                } else {
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public ActionBarDrawerToggle getDrawerToggle() {
        return actionBarDrawerToggle;
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

    @Override
    public void showBack(boolean value) {
        super.showBack(value);
        if (!value) {
            actionBarDrawerToggle.syncState();
        }
    }
}
