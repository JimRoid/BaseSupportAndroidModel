package com.easyapp.lib.event;

import androidx.fragment.app.Fragment;

public class MessageAddFragment {

    private Fragment fragment;

    public MessageAddFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
