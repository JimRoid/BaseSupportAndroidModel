package com.easyapp.ble;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import com.easyapp.ble.connect.BleConnectManager;
import com.easyapp.ble.connect.options.BleConnectOptions;
import com.easyapp.ble.connect.response.BleGeneralResponse;
import com.easyapp.ble.search.BluetoothSearchManager;
import com.easyapp.ble.search.SearchRequest;
import com.easyapp.ble.utils.BluetoothLog;

import java.util.UUID;



/**
 * Created by dingjikerbo on 2015/10/29.
 */
public class BluetoothServiceImpl extends IBluetoothService.Stub implements Handler.Callback {

    private static BluetoothServiceImpl sInstance;

    private Handler mHandler;

    private BluetoothServiceImpl() {
        mHandler = new Handler(Looper.getMainLooper(), this);
    }

    public static BluetoothServiceImpl getInstance() {
        if (sInstance == null) {
            synchronized (BluetoothServiceImpl.class) {
                if (sInstance == null) {
                    sInstance = new BluetoothServiceImpl();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void callBluetoothApi(int code, Bundle args, final IResponse response) throws RemoteException {
        Message msg = mHandler.obtainMessage(code, new BleGeneralResponse() {

            @Override
            public void onResponse(int code, Bundle data) {
                if (response != null) {
                    if (data == null) {
                        data = new Bundle();
                    }
                    try {
                        response.onResponse(code, data);
                    } catch (Throwable e) {
                        BluetoothLog.e(e);
                    }
                }
            }
        });

        args.setClassLoader(getClass().getClassLoader());
        msg.setData(args);
        msg.sendToTarget();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Bundle args = msg.getData();
        String mac = args.getString(Constants.EXTRA_MAC);
        UUID service = (UUID) args.getSerializable(Constants.EXTRA_SERVICE_UUID);
        UUID character = (UUID) args.getSerializable(Constants.EXTRA_CHARACTER_UUID);
        UUID descriptor = (UUID) args.getSerializable(Constants.EXTRA_DESCRIPTOR_UUID);
        byte[] value = args.getByteArray(Constants.EXTRA_BYTE_VALUE);
        BleGeneralResponse response = (BleGeneralResponse) msg.obj;

        switch (msg.what) {
            case Constants.CODE_CONNECT:
                BleConnectOptions options = args.getParcelable(Constants.EXTRA_OPTIONS);
                BleConnectManager.connect(mac, options, response);
                break;

            case Constants.CODE_DISCONNECT:
                BleConnectManager.disconnect(mac);
                break;

            case Constants.CODE_READ:
                BleConnectManager.read(mac, service, character, response);
                break;

            case Constants.CODE_WRITE:
                BleConnectManager.write(mac, service, character, value, response);
                break;

            case Constants.CODE_WRITE_NORSP:
                BleConnectManager.writeNoRsp(mac, service, character, value, response);
                break;

            case Constants.CODE_READ_DESCRIPTOR:
                BleConnectManager.readDescriptor(mac, service, character, descriptor, response);
                break;

            case Constants.CODE_WRITE_DESCRIPTOR:
                BleConnectManager.writeDescriptor(mac, service, character, descriptor, value, response);
                break;

            case Constants.CODE_NOTIFY:
                BleConnectManager.notify(mac, service, character, response);
                break;

            case Constants.CODE_UNNOTIFY:
                BleConnectManager.unnotify(mac, service, character, response);
                break;

            case Constants.CODE_READ_RSSI:
                BleConnectManager.readRssi(mac, response);
                break;

            case Constants.CODE_SEARCH:
                SearchRequest request = args.getParcelable(Constants.EXTRA_REQUEST);
                BluetoothSearchManager.search(request, response);
                break;

            case Constants.CODE_STOP_SESARCH:
                BluetoothSearchManager.stopSearch();
                break;

            case Constants.CODE_INDICATE:
                BleConnectManager.indicate(mac, service, character, response);
                break;

            case Constants.CODE_CLEAR_REQUEST:
                int clearType = args.getInt(Constants.EXTRA_TYPE, 0);
                BleConnectManager.clearRequest(mac, clearType);
                break;

            case Constants.CODE_REFRESH_CACHE:
                BleConnectManager.refreshCache(mac);
                break;
        }
        return true;
    }
}
