package com.easyapp.baseproject.lib.widgets.tabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.easyapp.baseproject.lib.R;

import java.io.Serializable;

/**
 * Created by easyapp_jim on 2016/6/6.
 */
public class EasyTab implements Serializable {
    public final static String EasyTab = "EasyTab";
    private final String TAB = "tab";
    private Class<? extends Fragment> clazz;
    private int tabImageResource = R.drawable.ic_camera;
    private String tabTextResource = "tab";

    public EasyTab setFragment(Class<? extends Fragment> clazz) {
        this.clazz = clazz;
        return this;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EasyTab, this);
        return bundle;
    }

    public String getTabTextResource() {
        return tabTextResource;
    }

    public void setTabTextResource(String tabTextResource) {
        this.tabTextResource = tabTextResource;
    }

    public int getTabImageResource() {
        return tabImageResource;
    }

    public void setTabImageResource(int tabImageResource) {
        this.tabImageResource = tabImageResource;
    }

    public Class<? extends Fragment> getClazz() {
        return clazz;
    }

    public String getTAB() {
        return TAB;
    }
}
