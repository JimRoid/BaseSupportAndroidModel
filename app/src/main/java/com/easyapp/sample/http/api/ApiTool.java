package com.easyapp.sample.http.api;


import android.content.Context;


import com.easyapp.database.EasyDB;
import com.easyapp.easyhttp.model.ResponseBase;
import com.easyapp.sample.BuildConfig;
import com.easyapp.sample.http.entity.EntityDiscuss;
import com.easyapp.sample.http.entity.EntityLogin;
import com.easyapp.easyhttp.BaseApiTool;
import com.easyapp.easyhttp.listener.EasyApiCallback;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by easyapp_jim on 2016/6/13.
 */
public class ApiTool extends BaseApiTool<ApiService> {

    public final static String HeadAuthorization = "Authorization";

    public ApiTool(Context context) {
        super(context);
    }

    @Override
    protected String initUrl() {
        return BuildConfig.ApiDomain;
    }

    @Override
    protected Class<ApiService> initService() {
        return ApiService.class;
    }

    public void login(String account, String password, EasyApiCallback<EntityLogin> apiCallback) {
        runCall(getServices().login(account, password), apiCallback);
    }

    public void register(String account, String password1, String password2, EasyApiCallback<ResponseBase> apiCallback) {
        runCall(getServices().register(account, password1, password2), apiCallback);
    }

    public void discussList(EasyApiCallback<EntityDiscuss> apiCallback) {
        runCall(getServices().discussList(), apiCallback);
    }

    public void discussCreate(String title, String content, ArrayList<String> imagePath, EasyApiCallback<ResponseBase> apiCallback) {

        List<MultipartBody.Part> map = new ArrayList<>();
        for (int i = 0; i < imagePath.size(); i++) {
            File file = new File(imagePath.get(i));
            if (file.exists()) {
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file[]", file.getName(), requestFile);
                map.add(body);
            } else {
                Logger.d("檔案不存在");
            }
        }

        RequestBody rTitle = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody rContent = RequestBody.create(MediaType.parse("text/plain"), content);
        runCall(getServices().discussCreate(EasyDB.getToken(getContext()), rTitle, rContent, map), apiCallback);
    }


}
