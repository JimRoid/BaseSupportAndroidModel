package com.easyapp.baseproject.lib.callback;

/**
 * 基本callback
 */
public interface iCallback {

    void callback();

    void callback(String value);

    void callback(Object object);

    void onSuccess();

    void onFail();

    void onComplete();

    void onComplete(String value);
}
