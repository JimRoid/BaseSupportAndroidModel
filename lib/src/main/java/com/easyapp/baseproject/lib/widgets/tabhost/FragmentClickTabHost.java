package com.easyapp.baseproject.lib.widgets.tabhost;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

public class FragmentClickTabHost extends FragmentTabHost {

    public FragmentClickTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);

        isCanSetCurrentTab = new IsCanSetCurrentTab() {
            @Override
            public boolean isCanSetCurrentTab(int index) {
                return true;
            }
        };
    }


    @Override
    public void setCurrentTab(int index) {
        if (index == getCurrentTab()) {
            if (onReclickListener != null) {
                onReclickListener.onClick(this);
            }
        } else {
            if (isCanSetCurrentTab.isCanSetCurrentTab(index)) {
                super.setCurrentTab(index);
            }
        }
    }


    public boolean isCanSetCurrentTab(int index) {
        return true;
    }

    public interface IsCanSetCurrentTab {
        boolean isCanSetCurrentTab(int index);
    }

    public void setisCanSetCurrentTab(IsCanSetCurrentTab i) {
        isCanSetCurrentTab = i;
    }

    protected IsCanSetCurrentTab isCanSetCurrentTab;

    protected OnClickListener onReclickListener;

    @Override
    public void setOnClickListener(OnClickListener l) {
        onReclickListener = l;
    }
}