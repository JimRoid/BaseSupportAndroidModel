package com.easyapp.lib.touchView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
        ArrayList<String> data = new ArrayList<>();
        data.addAll(bundle.getStringArrayList("PATH"));


        EasyPagerAdapter adapter = new EasyPagerAdapter(getSupportFragmentManager());
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
