package com.easyapp.lib.touchView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.easyapp.lib.R;
import com.easyapp.lib.widget.viewpager.adapter.v4.FragmentPagerItem;
import com.easyapp.lib.widget.viewpager.adapter.v4.FragmentPagerItemAdapter;
import com.easyapp.lib.widget.viewpager.adapter.v4.FragmentPagerItems;

import java.util.ArrayList;


/**
 * 顯示可滑動 touch view
 */
public class TouchViewActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        setContentView(R.layout.easyapp_pager_touchview);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.easyapp_viewpager);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("POSITION", 0);
        ArrayList<String> data = new ArrayList<>();
        data.addAll(bundle.getStringArrayList("PATH"));

        FragmentPagerItems pages = new FragmentPagerItems(this);
        for (String resource : data) {
            bundle = new Bundle();
            bundle.putString("PATH", resource);
            pages.add(FragmentPagerItem.of("", FragmentTouchView.class, bundle));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        if (position > pages.size()) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(position);
        }
    }


}
