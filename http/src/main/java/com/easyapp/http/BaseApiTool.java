package com.easyapp.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.easyapp.http.listener.EasyApiCallback;
import com.easyapp.http.model.ResponseBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 提供基本的 retrofit2 泛型callback
 * <p>
 * example: new initCallback<T>().getCallback(callback);
 */
public abstract class BaseApiTool<TServices> {

    protected Context context;
    protected Retrofit retrofit;
    protected OkHttpClient.Builder httpClient;
    protected TServices services;
    protected Class<TServices> typeClass;

    public BaseApiTool(Context context, Class<TServices> typeClass) {
        super();
        setContext(context);
        setTypeClass(typeClass);
        initHttpClient();
        Utils.validateServiceInterface(typeClass);

        Gson gson = new GsonBuilder().serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(initUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        services = retrofit.create(typeClass);
    }

    public void setServices(TServices services) {
        this.services = services;
    }

    public Class<TServices> getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class<TServices> typeClass) {
        this.typeClass = typeClass;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    /**
     * 取回 interface
     *
     * @return
     */
    protected TServices getServices() {
        return services;
    }

    protected void baseBodyCallback(ResponseBase responseBase) {
        if (BuildConfig.DEBUG) {
            Log.d("ApiTool.Class", new Gson().toJson(responseBase));
        }
    }

    protected void baseBodyCallbackFail(Throwable t) {
        if (BuildConfig.DEBUG) {
            try {
                Log.d("ApiTool.Class", t.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化ok http
     */
    private void initHttpClient() {
        httpClient = new OkHttpClient.Builder();
        // 重跑 api 的初始化
        httpClient.addInterceptor(new RetryInterceptor(getTotalRetries()));

        //設定timeout 值
        httpClient.connectTimeout(getConnectTimeOutSeconds(), TimeUnit.SECONDS);
        httpClient.readTimeout(getConnectTimeOutSeconds(), TimeUnit.SECONDS);

        //上傳timeout 值
        httpClient.writeTimeout(getWriteTimeOutSeconds(), TimeUnit.SECONDS);
    }

    private OkHttpClient getOkHttpClient() {
        return httpClient.build();
    }

    protected OkHttpClient.Builder getHttpClient() {
        return httpClient;
    }

    /**
     * read connect timeout seconds
     *
     * @return
     */
    protected int getConnectTimeOutSeconds() {
        return 10;
    }

    /**
     * write timeout seconds
     *
     * @return
     */
    protected int getWriteTimeOutSeconds() {
        return 30;
    }

    /**
     * timeout重新連接的次數
     *
     * @return
     */
    protected int getTotalRetries() {
        return 3;
    }

    /**
     * 運行 api服務
     *
     * @param call
     * @param easyApiCallback
     * @param <T>
     */
    protected <T> void runCall(Call<T> call, EasyApiCallback<T> easyApiCallback) {
        call.enqueue(new initCallback<>(easyApiCallback));
    }



    protected class initCallback<T> implements retrofit2.Callback<T> {

        private EasyApiCallback<T> easyApiCallback;

        private initCallback(EasyApiCallback<T> easyApiCallback) {
            super();
            this.easyApiCallback = easyApiCallback;
            try {
                this.easyApiCallback.initial();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            try {
                easyApiCallback.onResponse(call, response);
                if (response.isSuccessful() && response.code() == 200) {
                    Gson gson = new Gson();
                    String value = gson.toJson(response.body());
                    ResponseBase responseBase = gson.fromJson(value, ResponseBase.class);
                    baseBodyCallback(responseBase);
                    if (responseBase.getStatus() == 200) {
                        easyApiCallback.onCallback(response.body());
                    } else {
                        easyApiCallback.onFail(response.body());
                    }
                } else {
                    easyApiCallback.onFail(response.body());
                }
                easyApiCallback.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (BuildConfig.DEBUG) {
                t.printStackTrace();
            }
            try {
                baseBodyCallbackFail(t);
                easyApiCallback.onFail(t);
                easyApiCallback.onComplete();
                if (!Utils.isNetworkAvailable(getContext())) {
                    Toast.makeText(getContext(), getContext().getString(R.string.network_message_content), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重試interceptor
     */
    public class RetryInterceptor implements Interceptor {

        private int maxRetry;
        private int retryNum = 0;

        RetryInterceptor(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            okhttp3.Response response = chain.proceed(request);

            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                response = chain.proceed(request);
            }
            return response;
        }
    }

    protected abstract String initUrl();

    public interface Callback<T> {
        void onResponse(Call<T> call, Response<T> response);
    }
}
