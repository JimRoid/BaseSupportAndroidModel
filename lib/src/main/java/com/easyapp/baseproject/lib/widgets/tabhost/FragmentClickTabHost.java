package com.easyapp.baseproject.lib.widgets.tabhost;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

public class FragmentClickTabHost extends FragmentTabHost {

	public FragmentClickTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setCurrentTab(int index) {
		if (index == getCurrentTab()) {
			if (onReclickListener != null) {
				onReclickListener.onClick(this);
			}
		} else {
			super.setCurrentTab(index);
		}
	}

	protected OnClickListener onReclickListener;

	@Override
	public void setOnClickListener(OnClickListener l) {
		onReclickListener = l;
	}
}