package com.easyapp.http.listener;

import retrofit2.Call;
import retrofit2.Response;

public interface iEasyApiCallback<T> {

    void initial();

    void onResponse(Call<T> call, Response<T> response);

    void onCallback(T t);

    void onFail(T t);

    void onFail(Throwable t);

    void onComplete();
}