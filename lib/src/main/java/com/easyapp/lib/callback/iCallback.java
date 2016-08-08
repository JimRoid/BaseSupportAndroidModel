package com.easyapp.lib.callback;

/**
 * 基本callback
 */
public interface iCallback {

    void initCallback();

    void callback();

    void callback(String value);

    void callback(Object object);

    void onSuccess();

    void onFail();

    void onFail(Object object);

    void onComplete();

    void onComplete(String value);
}
