package com.easyapp.lib.callback;

/**
 * 基本callback
 */
public interface iCallback<T> {

    void initCallback();


    void callback(T object);

    void onFail(T object);

    void onComplete();

}
