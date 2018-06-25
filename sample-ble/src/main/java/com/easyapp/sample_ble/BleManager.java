package com.easyapp.sample_ble;

import android.app.Application;

import com.easyapp.ble.BluetoothClient;
import com.easyapp.ble.connect.response.BleConnectResponse;
import com.easyapp.ble.search.SearchRequest;
import com.easyapp.ble.search.response.SearchResponse;
import com.orhanobut.logger.Logger;

public class BleManager {

    private static BluetoothClient bluetoothClient;

    public BleManager() {
        super();
    }

    private BleManager(Application application) {
        super();
        bluetoothClient = new BluetoothClient(application);
        if (bluetoothClient.isBleSupported()) {
            if (bluetoothClient.isBluetoothOpened()) {
                Logger.d("isOpen");
            } else {
                bluetoothClient.openBluetooth();
            }
        }
    }

    public static void initial(Application application) {
        new BleManager(application);
    }


    public static void search(SearchRequest searchRequest, SearchResponse searchResponse) {
        try {
            bluetoothClient.search(searchRequest, searchResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connect(String mac, BleConnectResponse bleConnectResponse) {
        try {
            bluetoothClient.connect(mac, bleConnectResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
