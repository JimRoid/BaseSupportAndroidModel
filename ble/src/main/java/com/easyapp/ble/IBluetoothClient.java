package com.easyapp.ble;


import com.easyapp.ble.connect.listener.BleConnectStatusListener;
import com.easyapp.ble.connect.listener.BluetoothStateListener;
import com.easyapp.ble.connect.options.BleConnectOptions;
import com.easyapp.ble.connect.response.BleConnectResponse;
import com.easyapp.ble.connect.response.BleNotifyResponse;
import com.easyapp.ble.connect.response.BleReadResponse;
import com.easyapp.ble.connect.response.BleReadRssiResponse;
import com.easyapp.ble.connect.response.BleUnnotifyResponse;
import com.easyapp.ble.connect.response.BleWriteResponse;
import com.easyapp.ble.receiver.listener.BluetoothBondListener;
import com.easyapp.ble.search.SearchRequest;
import com.easyapp.ble.search.response.SearchResponse;

import java.util.UUID;

/**
 * Created by dingjikerbo on 2016/8/25.
 */
public interface IBluetoothClient {

    void connect(String mac, BleConnectOptions options, BleConnectResponse response);

    void disconnect(String mac);

    void registerConnectStatusListener(String mac, BleConnectStatusListener listener);

    void unregisterConnectStatusListener(String mac, BleConnectStatusListener listener);

    void read(String mac, UUID service, UUID character, BleReadResponse response);

    void write(String mac, UUID service, UUID character, byte[] value, BleWriteResponse response);

    void readDescriptor(String mac, UUID service, UUID character, UUID descriptor, BleReadResponse response);

    void writeDescriptor(String mac, UUID service, UUID character, UUID descriptor, byte[] value, BleWriteResponse response);

    void writeNoRsp(String mac, UUID service, UUID character, byte[] value, BleWriteResponse response);

    void notify(String mac, UUID service, UUID character, BleNotifyResponse response);

    void unnotify(String mac, UUID service, UUID character, BleUnnotifyResponse response);

    void indicate(String mac, UUID service, UUID character, BleNotifyResponse response);

    void unindicate(String mac, UUID service, UUID character, BleUnnotifyResponse response);

    void readRssi(String mac, BleReadRssiResponse response);

    void search(SearchRequest request, SearchResponse response);

    void stopSearch();

    void registerBluetoothStateListener(BluetoothStateListener listener);

    void unregisterBluetoothStateListener(BluetoothStateListener listener);

    void registerBluetoothBondListener(BluetoothBondListener listener);

    void unregisterBluetoothBondListener(BluetoothBondListener listener);

    void clearRequest(String mac, int type);

    void refreshCache(String mac);
}
