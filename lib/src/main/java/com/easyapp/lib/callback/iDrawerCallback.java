package com.easyapp.lib.callback;




import androidx.appcompat.app.ActionBarDrawerToggle;

/**
 * Created by easyapp_jim on 2016/4/14.
 */
public interface iDrawerCallback {

    ActionBarDrawerToggle getDrawerToggle();

    void openDrawer(int gravity);

    void closeDrawer(int gravity);

    void setDrawerLock();

    void setDrawerUnLock();

    void setDrawerState(int state);
}
