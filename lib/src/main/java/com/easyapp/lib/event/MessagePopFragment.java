package com.easyapp.lib.event;

import androidx.fragment.app.Fragment;

public class MessagePopFragment {

    private int depth;

    public MessagePopFragment(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
