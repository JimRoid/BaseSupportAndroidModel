package com.easyapp.ble;

import android.Manifest;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.easyapp.ble.search.SearchRequest;
import com.easyapp.ble.search.SearchResult;
import com.easyapp.ble.search.response.SearchResponse;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class SelectBleActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, SwipeRefreshLayout.OnRefreshListener {

    private String Tag = "SelectBleActivity.Class";
    /**
     * permission request code
     */
    public static final int ACCESS_COARSE_LOCATION = 1010;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private BleAdapter bleAdapter;
    private BluetoothClient bluetoothClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ble);
        setTitle(R.string.search);

        bluetoothClient = new BluetoothClient(this);
        bleAdapter = new BleAdapter(this);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        listView = findViewById(R.id.listView);
        listView.setAdapter(bleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchResult searchResult = bleAdapter.getItem(position);
                Log.d(Tag, searchResult.getName());
            }
        });
    }


    private void initBle() {
        if (bluetoothClient.isBleSupported()) {
            if (!bluetoothClient.isBluetoothOpened()) {
                bluetoothClient.openBluetooth();
            }
        }
        search();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkWritePermission();
        } else {
            initBle();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            search();
            return true;
        } else if (id == R.id.action_stop) {
            stop();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        search();
    }

    private void search() {
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(50000)
                .build();

        bluetoothClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {
                Log.d(Tag, "onSearchStarted");
                bleAdapter.clear();
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Log.d(Tag, "onDeviceFounded");
                bleAdapter.add(device);
            }

            @Override
            public void onSearchStopped() {
                Log.d(Tag, "onSearchStopped");
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onSearchCanceled() {
                Log.d(Tag, "onSearchCanceled");
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void stop() {
        bluetoothClient.stopSearch();
    }

    @AfterPermissionGranted(ACCESS_COARSE_LOCATION)
    private void checkWritePermission() {
        String[] params = {Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, params)) {
            initBle();
        } else {
            EasyPermissions.requestPermissions(this, "該應用需要地理位置權限", ACCESS_COARSE_LOCATION, params);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        String[] params = {Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有該權限，此應用無法正常工作。打開應用設置界面以修改權限")
                    .setTitle("必須權限")
                    .build()
                    .show();
        } else if (!EasyPermissions.hasPermissions(this, params)) {
            finish();
        }
    }
}
