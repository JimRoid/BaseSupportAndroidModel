package com.easyapp.lib.http;

import android.content.Context;

import com.easyapp.lib.callback.Callback;
import com.easyapp.lib.tool.Utils;
import com.google.gson.Gson;
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

    private boolean showDebug = true;

    private Context context;

    protected abstract String initUrl();

    protected abstract Class<TServices> initService();

    protected TServices services;

    public Context getContext() {
        return context;
    }

    public void setShowDebug(boolean showDebug) {
        this.showDebug = showDebug;
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


    public class initCallback<T> implements BaseApiCallback<T> {
        @Override
        public retrofit2.Callback<T> getCallback(final Callback<T> callback) {
            //初始化
            callback.initCallback();

            return new retrofit2.Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    if (showDebug) {
                        Logger.d("response.code():" + response.code() + "");
                        Gson gson = new Gson();
                        Logger.d("" + gson.toJson(response.body()));
                    }
                    if (response.isSuccessful() && response.code() == 200) {
                        callback.callback(response.body());
                    } else {
                        callback.onFail(response.body());
                    }
                    callback.onComplete();
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    if (showDebug) {
                        Logger.d("onFailure");
                    }
                    callback.onFail();
                    callback.onComplete();
                }
            };
        }
    }
}
