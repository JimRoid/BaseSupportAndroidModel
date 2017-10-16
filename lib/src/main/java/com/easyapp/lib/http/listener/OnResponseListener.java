package com.easyapp.lib.http.listener;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by easyapp_jim on 2017/10/16.
 */

public interface OnResponseListener<T> {
    void onResponse(Call<T> call, Response<T> response);
}
