package com.easyapp.baseproject.lib.baseFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.widgets.tabhost.EasyTab;
import com.orhanobut.logger.Logger;

/**
 * 基本的 tab fragment 版面
 */
public class BaseTabParentFragment extends BaseToolbarFragment {
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.easyapp_fragment_base_tab, container, false);
        initView();
        return view;
    }

    private void initView() {
        if (getChildFragmentManager().findFragmentByTag("tab") == null) {
            Logger.d(" == null");
            EasyTab easyTab = (EasyTab) getArguments().getSerializable(EasyTab.EasyTab);
            ReplaceFragment(Fragment.instantiate(getContext(), easyTab.getClazz().getName(), new Bundle()));
        }
    }

    public boolean onBackPressed() {
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            if (getChildFragmentManager().getBackStackEntryCount() == 1) {
//                setToolbar_menu(Common.HIDE_BACK);
            }
            getChildFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

    @Override
    protected boolean isChildMode() {
        return true;
    }

    @Override
    protected int getChildId() {
        return R.id.tab_content;
    }
}
