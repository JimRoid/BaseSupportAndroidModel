package com.easyapp.lib.http;

import android.content.Context;

import com.easyapp.lib.callback.Callback;
import com.easyapp.lib.tool.Utils;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 提供基本的 retrofit2 泛型callback
 * <p>
 * example: new initCallback<T>().getCallback(callback);
 */
public abstract class BaseApiTool<TServices> {

    private Context context;

    protected abstract String initUrl();

    protected abstract Class<TServices> initService();

    protected TServices services;

    public Context getContext() {
        return context;
    }

    public BaseApiTool(Context context) {
        super();
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(initUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Utils.validateServiceInterface(this.initService());
        services = retrofit.create(initService());
    }

    interface BaseApiCallback<T> {
        retrofit2.Callback<T> getCallback(final Callback callback);
    }

    public class initCallback<T> implements BaseApiCallback<T> {
        @Override
        public retrofit2.Callback<T> getCallback(final Callback callback) {
            //初始化
            callback.initCallback();

            return new retrofit2.Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        callback.callback(response.body());
                        callback.onComplete();
                    } else {
                        callback.onFail(response.code() + "");
                        callback.onComplete();
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    Logger.d("onFailure");
                    callback.onFail("error");
                    callback.onComplete();
                }
            };
        }
    }
}
