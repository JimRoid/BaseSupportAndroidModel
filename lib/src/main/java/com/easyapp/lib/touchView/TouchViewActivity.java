package com.easyapp.lib.touchView;

import android.Manifest;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.easyapp.lib.widget.viewPager.EasyPagerAdapter;
import com.easyapp.lib.R;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 顯示可滑動 touch view
 */
public class TouchViewActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    /**
     * permission request code
     */
    public static final int WRITE_EXTERNAL_STORAGE = 1010;

    // view pager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pager_touchview);
        checkWritePermission();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
    }

    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private void checkWritePermission() {
        String[] params = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, params)) {

        } else {
            EasyPermissions.requestPermissions(this, "該應用需要讀寫權限", WRITE_EXTERNAL_STORAGE, params);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        String[] params = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有該權限，此應用無法進行下載圖片。打開應用設置界面以修改權限")
                    .setTitle("必須權限")
                    .build()
                    .show();
        } else if (!EasyPermissions.hasPermissions(this, params)) {
            finish();
        }
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
        boolean isShowDownload = bundle.getBoolean("isShowDownload", false);
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
            adapter.addFragment(FragmentTouchView.getInstance("http", resource, isShowDownload));
        }

        viewPager.setAdapter(adapter);
        if (position >= adapter.getCount()) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(position);
        }
    }


}
