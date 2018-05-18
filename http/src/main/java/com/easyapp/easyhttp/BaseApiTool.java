package com.easyapp.easyhttp;

import android.content.Context;
import android.widget.Toast;

import com.easyapp.easyhttp.listener.EasyApiCallback;
import com.easyapp.easyhttp.model.ResponseBase;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
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


    private ArrayList<Call> callArrayList;

    private Context context;

    protected Retrofit retrofit;

    protected abstract String initUrl();

    protected abstract Class<TServices> initService();

    private TServices services;

    private OkHttpClient.Builder httpClient;

    public Context getContext() {
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

    public BaseApiTool(Context context) {
        super();
        this.context = context;
        callArrayList = new ArrayList<>();
        initHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(initUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        Utils.validateServiceInterface(this.initService());

        services = retrofit.create(initService());
    }

    /**
     * 初始化ok http
     */
    private void initHttpClient() {
        httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));
        }

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
        if (callArrayList.size() > 0) {
            if (call.request().url().toString().equals(callArrayList.get(callArrayList.size() - 1).request().url().toString())) {
                return;
            }
        }
        callArrayList.add(call);
        call.enqueue(new initCallback<>(easyApiCallback));
    }

    protected <T> void runCallSingle(Call<T> call, EasyApiCallback<T> easyApiCallback) {
        if (callArrayList.size() > 0) {
            return;
        }
        callArrayList.add(call);
        call.enqueue(new initCallback<>(easyApiCallback));
    }


    private class initCallback<T> implements retrofit2.Callback<T> {

        private EasyApiCallback<T> easyApiCallback;

        initCallback(EasyApiCallback<T> easyApiCallback) {
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
            callArrayList.clear();
            call.cancel();
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (BuildConfig.DEBUG) {
                t.printStackTrace();
            }

            try {
                easyApiCallback.onFail(t);
                easyApiCallback.onComplete();
                if (!Utils.isNetworkAvailable(getContext())) {
                    Toast.makeText(getContext(), getContext().getString(R.string.network_message_content), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            callArrayList.clear();
            call.cancel();
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
}
