package com.easyapp.ble.receiver.listener;

/**
 * Created by dingjikerbo on 17/1/14.
 */

public abstract class BluetoothReceiverListener extends AbsBluetoothListener {

    abstract public String getName();

    @Override
    final public void onSyncInvoke(Object... args) {
        throw new UnsupportedOperationException();
    }
}
