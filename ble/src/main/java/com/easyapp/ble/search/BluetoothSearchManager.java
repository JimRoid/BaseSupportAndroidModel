package com.easyapp.ble.search;

import android.os.Bundle;

import com.easyapp.ble.Constants;
import com.easyapp.ble.connect.response.BleGeneralResponse;
import com.easyapp.ble.search.response.BluetoothSearchResponse;


/**
 * Created by dingjikerbo on 2016/8/28.
 */
public class BluetoothSearchManager {

    public static void search(SearchRequest request, final BleGeneralResponse response) {
        BluetoothSearchRequest requestWrapper = new BluetoothSearchRequest(request);
        BluetoothSearchHelper.getInstance().startSearch(requestWrapper, new BluetoothSearchResponse() {
            @Override
            public void onSearchStarted() {
                response.onResponse(Constants.SEARCH_START, null);
            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.EXTRA_SEARCH_RESULT, device);
                response.onResponse(Constants.DEVICE_FOUND, bundle);
            }

            @Override
            public void onSearchStopped() {
                response.onResponse(Constants.SEARCH_STOP, null);
            }

            @Override
            public void onSearchCanceled() {
                response.onResponse(Constants.SEARCH_CANCEL, null);
            }
        });
    }

    public static void stopSearch() {
        BluetoothSearchHelper.getInstance().stopSearch();
    }
}
