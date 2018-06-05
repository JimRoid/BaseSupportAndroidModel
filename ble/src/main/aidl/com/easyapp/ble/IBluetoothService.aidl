// IBluetoothManager.aidl
package com.easyapp.ble;

// Declare any non-default types here with import statements

import com.easyapp.ble.IResponse;

interface IBluetoothService {
    void callBluetoothApi(int code, inout Bundle args, IResponse response);
}
