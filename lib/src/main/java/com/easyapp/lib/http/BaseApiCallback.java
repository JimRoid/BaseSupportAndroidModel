package com.easyapp.lib.http;

import com.easyapp.lib.callback.Callback;

public interface BaseApiCallback<T> {
    retrofit2.Callback<T> getCallback(final Callback<T> callback);
}