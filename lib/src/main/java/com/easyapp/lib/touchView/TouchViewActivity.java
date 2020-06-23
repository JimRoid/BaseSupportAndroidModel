package com.easyapp.lib.touchView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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


    public static void startActivity(Context context, int position, String type, ArrayList<String> path, boolean isShowDownload) {
        ArrayList<String> arrayList = new ArrayList<>(path);
        Intent intent = new Intent(context, TouchViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putStringArrayList("path", arrayList);
        bundle.putInt("position", position);
        bundle.putBoolean("isShowDownload", isShowDownload);
        intent.putExtras(bundle);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

    public static void startActivity(Context context, int position, String type, String path, boolean isShowDownload) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(path);
        Intent intent = new Intent(context, TouchViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putStringArrayList("path", arrayList);
        bundle.putInt("position", position);
        bundle.putBoolean("isShowDownload", isShowDownload);
        intent.putExtras(bundle);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException var3) {
            var3.printStackTrace();
        }
    }

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

    private String getType() {
        return getIntent().getExtras().getString("type", "http");
    }

    private boolean isShowDownload() {
        return getIntent().getExtras().getBoolean("isShowDownload", false);
    }

    private int getPosition() {
        return getIntent().getExtras().getInt("position", 0);
    }

    private ArrayList<String> getPath() {
        return getIntent().getExtras().getStringArrayList("path");
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
        EasyPagerAdapter adapter = new EasyPagerAdapter(getSupportFragmentManager());
        if (getPath() == null) {
            return;
        }
        for (String resource : getPath()) {
            adapter.addFragment(FragmentTouchView.getInstance(getType(), resource, isShowDownload()));
        }

        viewPager.setAdapter(adapter);
        if (getPosition() >= adapter.getCount()) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(getPosition());
        }
    }


}
