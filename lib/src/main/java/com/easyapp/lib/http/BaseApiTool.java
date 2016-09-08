package com.easyapp.lib.http;

import com.easyapp.lib.callback.Callback;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by easyapp_jim on 2016/9/6.
 */
public class BaseApiTool {

    public <T> retrofit2.Callback getCallback(final Callback callback) {
        callback.initCallback();
        return new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Gson gson = new Gson();
                    String raw = gson.toJson(response.body());
                    ItemBase message = gson.fromJson(raw, ItemBase.class);

                    if (message == null) {
                        callback.onFail();
                        callback.onComplete();
                        return;
                    }

                    if (message.getStatus() == 200) {
                        callback.callback(response.body());
                    } else {
                        callback.onFail(response.body());
                    }
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
                t.printStackTrace();
            }
        };
    }
}
