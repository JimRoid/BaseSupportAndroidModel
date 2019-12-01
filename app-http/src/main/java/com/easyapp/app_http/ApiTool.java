package com.easyapp.app_http;

import android.content.Context;

import com.easyapp.http.BaseApiTool;
import com.easyapp.http.listener.EasyApiCallback;
import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ApiTool extends BaseApiTool<ApiService> {

    public ApiTool(Context context) {
        super(context, ApiService.class);
    }

    @Override
    protected String initUrl() {
        return "https://test2.easyapp.com.tw/sample-api-test/";
    }

    public void upload(ArrayList<String> path, EasyApiCallback<JsonObject> callback) {
        List<MultipartBody.Part> map = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            File file = new File(path.get(i));
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
        runCall(getServices().uploadImage(map), callback);
    }

}
