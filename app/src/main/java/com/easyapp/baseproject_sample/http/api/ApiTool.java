package com.easyapp.baseproject_sample.http.api;


import com.easyapp.lib.callback.Callback;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by easyapp_jim on 2016/6/13.
 */
public class ApiTool {

    private String test_domain = "http://139.59.246.193/treasure/api/v1/index.php/";

    private ApiService service;
    private Retrofit retrofit;

    public ApiTool() {
        super();
        retrofit = new Retrofit.Builder()
                .baseUrl(test_domain)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }


    /**
     * 取回商品列表資料
     *
     * @param type
     * @param p_name
     * @param page
     * @param callback
     */
    public void getProductList(String type, String p_name, String page, Callback callback) {
        service.getProductList(type, p_name, page, "40").enqueue(getCallback(callback));
    }

    public <T> retrofit2.Callback getCallback(final Callback callback) {
        callback.initCallback();

        return new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.callback(response.body());
                    callback.onComplete();
                } else {
                    callback.onFail();
                    callback.onComplete();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Logger.d("onFailure");
                callback.onFail();
                callback.onComplete();
            }
        };
    }
}
