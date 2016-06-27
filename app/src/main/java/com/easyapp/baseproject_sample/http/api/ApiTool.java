package com.easyapp.baseproject_sample.http.api;


import com.easyapp.baseproject.lib.callback.Callback;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by easyapp_jim on 2016/6/13.
 */
public class ApiTool {

    private String test_domain = "http://jsonplaceholder.typicode.com";

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


    public void getPhotos(Callback callback) {
        service.getPhotos().enqueue(getCallback(callback));
    }

    public void getPosts(Callback callback) {
        service.getPosts().enqueue(getCallback(callback));
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
