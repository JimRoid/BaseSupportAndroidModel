package com.easyapp.lib.http.listener;

import retrofit2.Call;

/**
 * Created by easyapp_jim on 2017/10/24.
 */

public interface OnFailureListener<T> {

    void onFailure(Call<T> call, Throwable t);
}
