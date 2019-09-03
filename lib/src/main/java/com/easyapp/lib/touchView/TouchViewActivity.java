package com.easyapp.lib.touchView;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.easyapp.lib.widget.viewPager.EasyPagerAdapter;
import com.easyapp.lib.R;

import java.util.ArrayList;


/**
 * 顯示可滑動 touch view
 */
public class TouchViewActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pager_touchview);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.easyapp_viewpager);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        int position = bundle.getInt("POSITION", 0);
        if (bundle.getStringArrayList("PATH") == null) {
            return;
        }
        ArrayList<String> data = bundle.getStringArrayList("PATH");
        EasyPagerAdapter adapter = new EasyPagerAdapter(getSupportFragmentManager());
        if (data == null) {
            return;
        }
        for (String resource : data) {
            adapter.addFragment(FragmentTouchView.instance(resource));
        }

        viewPager.setAdapter(adapter);
        if (position >= adapter.getCount()) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(position);
        }
    }


}
