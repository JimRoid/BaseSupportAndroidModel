package com.easyapp.lib.http.listener;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by easyapp_jim on 2017/10/14.
 */

public class EasyApiCallback<T> implements iEasyApiCallback<T> {
    @Override
    public void initial() {

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onCallback(T t) {

    }

    @Override
    public void onFail(T t) {

    }

    @Override
    public void onFail(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
