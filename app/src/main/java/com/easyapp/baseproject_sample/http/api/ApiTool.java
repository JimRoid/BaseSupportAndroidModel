package com.easyapp.baseproject_sample.http.api;


import android.content.Context;



import com.easyapp.baseproject_sample.http.entity.ItemSchool;
import com.easyapp.easyhttp.BaseApiTool;
import com.easyapp.easyhttp.listener.EasyApiCallback;
import com.easyapp.easyhttp.listener.OnResponseListener;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by easyapp_jim on 2016/6/13.
 */
public class ApiTool extends BaseApiTool<ApiService> {

    public ApiTool(Context context) {
        super(context);
        addOnResponseListener(new OnResponseListener() {
            @Override
            public void onResponse(Call call, Response response) {
                Logger.d(new Gson().toJson(response.body()));
            }
        });
    }

    @Override
    protected String initUrl() {
        return "https://test2.easyapp.com.tw/easy-school/api/";
    }

    @Override
    protected Class<ApiService> initService() {
        return ApiService.class;
    }

    /**
     * 取回商品列表資料
     *
     * @param type
     * @param p_name
     * @param page
     * @param callback
     */
    public void getSchool(EasyApiCallback<ItemSchool> callback) {
        runCall(getServices().getSchool(), callback);
    }


}
