package com.easyapp.lib.http.listener;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 取回正常回傳監聽器
 */

public interface OnResponseListener<T> {
    void onResponse(Call<T> call, Response<T> response);
}
