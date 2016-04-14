package com.easyapp.baseproject.lib.baseActivity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.callback.iDrawerCallback;

/**
 * 基本的側邊欄activity
 */
public abstract class baseDrawerMainActivity extends baseMainActivity implements iDrawerCallback {

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
    public void openDrawer(int gravity) {
        drawerLayout.openDrawer(gravity);
    }

    @Override
    public void closeDrawer(int gravity) {
        drawerLayout.closeDrawer(gravity);
    }
}
