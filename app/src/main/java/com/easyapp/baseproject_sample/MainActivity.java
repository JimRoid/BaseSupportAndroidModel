package com.easyapp.baseproject_sample;

import android.content.Intent;
import android.view.View;

import com.easyapp.baseproject.lib.baseActivity.BaseDrawerMainActivity;

public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {
        setTitle("BaseProject Sample");
//        ReplaceFragment(new SampleFragment());
        ReplaceFragment(new FragmentViewPage());
//        ReplaceFragment(new FragmentViewPageCircle());

        addLeftDrawer(new FragmentImage());
    }


    public void openDrawerActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainDrawerActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnAddFragment() {
        super.OnAddFragment();
        showBack(true);
    }
}
