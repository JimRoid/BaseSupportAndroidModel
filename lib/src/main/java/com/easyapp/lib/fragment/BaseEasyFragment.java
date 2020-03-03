package com.easyapp.lib.fragment;


import androidx.fragment.app.Fragment;

import com.easyapp.lib.backpressHelper.FragmentBackHandler;
import com.easyapp.lib.event.MessageAddFragment;
import com.easyapp.lib.event.MessagePopFragment;
import com.easyapp.lib.event.MessageReplaceFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * 簡單可支援fragment 切換的base
 */
public abstract class BaseEasyFragment extends BaseFragment implements FragmentBackHandler {

    protected void addFragment(Fragment fragment) {
        EventBus.getDefault().post(new MessageAddFragment(fragment));
    }

    protected void replaceFragment(Fragment fragment) {
        EventBus.getDefault().post(new MessageReplaceFragment(fragment));
    }

    protected void popFragment(int depth) {
        EventBus.getDefault().post(new MessagePopFragment(depth));
    }


}
