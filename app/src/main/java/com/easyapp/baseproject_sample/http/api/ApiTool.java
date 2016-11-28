package com.easyapp.baseproject_sample.http.api;


import android.content.Context;

import com.easyapp.baseproject_sample.http.entity.ItemProduct;
import com.easyapp.lib.callback.Callback;
import com.easyapp.lib.http.BaseApiTool;

/**
 * Created by easyapp_jim on 2016/6/13.
 */
public class ApiTool extends BaseApiTool<ApiService> {

    public ApiTool(Context context) {
        super(context);
    }

    @Override
    protected String initUrl() {
        return "http://139.59.246.193/treasure/api/v1/index.php/";
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
    public void getProductList(String type, String p_name, String page, Callback callback) {
        services.getProductList(type, p_name, page, "40").enqueue(new initCallback<ItemProduct>().getCallback(callback));
    }


}
