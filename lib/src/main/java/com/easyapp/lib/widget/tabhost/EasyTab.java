package com.easyapp.lib.widget.tabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.easyapp.lib.R;

import java.io.Serializable;

/**
 * 提供tab 使用
 */
public class EasyTab implements Serializable {
    public final static String EasyTab = "EasyTab";
    private final String TAB = "tab";
    private Class<? extends Fragment> clazz;
    private int tabImageResource = 0;
    private int tabTextColorResource = R.drawable.tab_text_color;
    private int tabPressStateDrawable = R.drawable.tab_default_background;
    private String tabTextResource = "tab";

    private int tabLayout = R.layout.custom_tab_icon_and_text;
    private int tabImageId = R.id.iv_tab_icon;
    private int tabTextId = R.id.tv_tab;


    public EasyTab setFragment(Class<? extends Fragment> clazz) {
        this.clazz = clazz;
        return this;
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EasyTab, this);
        return bundle;
    }

    public int getTabLayout() {
        return tabLayout;
    }

    public EasyTab setTabLayout(int tabLayout) {
        this.tabLayout = tabLayout;
        return this;
    }

    public int getTabImageId() {
        return tabImageId;
    }

    public int getTabTextId() {
        return tabTextId;
    }

    public EasyTab setTabImageId(int tabImageId) {
        this.tabImageId = tabImageId;
        return this;
    }

    public EasyTab setTabTextId(int tabTextId) {
        this.tabTextId = tabTextId;
        return this;
    }

    public String getTabTextResource() {
        return tabTextResource;
    }

    public int getTabTextColorResource() {
        return tabTextColorResource;
    }

    public EasyTab setTabTextColorResource(int tabTextColorResource) {
        this.tabTextColorResource = tabTextColorResource;
        return this;
    }

    public EasyTab setTabTextResource(String tabTextResource) {
        this.tabTextResource = tabTextResource;
        return this;
    }

    public int getTabImageResource() {
        return tabImageResource;
    }

    public EasyTab setTabImageResource(int tabImageResource) {
        this.tabImageResource = tabImageResource;
        return this;
    }

    public EasyTab setTabPressStateDrawable(int tabPressStateDrawable) {
        this.tabPressStateDrawable = tabPressStateDrawable;
        return this;
    }

    public int getTabPressStateDrawable() {
        return tabPressStateDrawable;
    }

    public Class<? extends Fragment> getClazz() {
        return clazz;
    }

    public String getTAB() {
        return TAB;
    }
}
