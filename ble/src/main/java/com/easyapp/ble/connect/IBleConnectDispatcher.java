package com.easyapp.ble.connect;


import com.easyapp.ble.connect.request.BleRequest;

public interface IBleConnectDispatcher {

    void onRequestCompleted(BleRequest request);
}
