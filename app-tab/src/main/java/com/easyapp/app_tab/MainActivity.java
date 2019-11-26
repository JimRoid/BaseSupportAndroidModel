package com.easyapp.app_tab;

import android.view.View;
import android.widget.ImageView;

import com.easyapp.lib.activity.BaseTabActivity;
import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends BaseTabActivity {


    @Override
    protected void initTab() {
//        AppBarLayout appLayoutHead = findViewById(R.id.appLayoutHead);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.picture1);

//        appLayoutHead.addView(imageView);

        addTab("1", new BlankFragment1());
        addTab("2", new BlankFragment2());
        addTab("3", new BlankFragment3());
        addTab("4", new BlankFragment1());
//        addTab("5", new BlankFragment1());
    }

}
