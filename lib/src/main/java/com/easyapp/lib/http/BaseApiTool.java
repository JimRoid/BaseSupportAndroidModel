package com.easyapp.lib.http;

import android.content.Context;
import android.widget.Toast;

import com.easyapp.lib.R;
import com.easyapp.lib.http.listener.EasyApiCallback;
import com.easyapp.lib.http.listener.OnResponseListener;

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

import static com.easyapp.lib.http.Utils.validateServiceInterface;

/**
 * 提供基本的 retrofit2 泛型callback
 * <p>
 * example: new initCallback<T>().getCallback(callback);
 */
public abstract class BaseApiTool<TServices> {

    private ArrayList<Call> callArrayList;

    private ArrayList<OnResponseListener> onResponseListeners;

    private boolean showDebug = true;

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

    public void setShowDebug(boolean showDebug) {
        this.showDebug = showDebug;
    }

    public BaseApiTool(Context context) {
        super();

        this.context = context;

        callArrayList = new ArrayList<>();
        onResponseListeners = new ArrayList<>();

        initHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(initUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        validateServiceInterface(this.initService());

        services = retrofit.create(initService());
    }

    /**
     * 初始化ok http
     */
    private void initHttpClient() {
        httpClient = new OkHttpClient.Builder();

        // api logger
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC));
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
        callArrayList.add(call);
        call.enqueue(new initCallback<T>(easyApiCallback));
    }

    public <T> void addOnResponseListener(OnResponseListener<T> listener) {
        onResponseListeners.add(listener);
    }

    public void clearOnResponseListener() {
        onResponseListeners.clear();
    }

    /**
     * 取消所有的請求
     */
    public void cancel() {
        for (Call call : callArrayList) {
            if (call.isExecuted()) {
                call.cancel();
            }
        }
        callArrayList.clear();
    }

    private class initCallback<T> implements retrofit2.Callback<T> {

        private EasyApiCallback<T> easyApiCallback;

        public initCallback(EasyApiCallback<T> easyApiCallback) {
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

                //共用中間監聽器
                for (OnResponseListener onResponseListener : onResponseListeners) {
                    onResponseListener.onResponse(call, response);
                }

                if (response.isSuccessful() && response.code() == 200) {
                    easyApiCallback.onCallback(response.body());
                } else {
                    easyApiCallback.onFail(response.body());
                }
                call.cancel();
                easyApiCallback.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (showDebug) {
                t.printStackTrace();
            }
            call.cancel();
            try {
                easyApiCallback.onFail(t);
                easyApiCallback.onComplete();

                if (!Utils.isNetworkAvailable(getContext())) {
                    cancel();
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

        public int maxRetry;
        private int retryNum = 0;

        public RetryInterceptor(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            System.out.println("retryNum=" + retryNum);

            okhttp3.Response response = chain.proceed(request);

            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                response = chain.proceed(request);
            }
            return response;
        }
    }

}
