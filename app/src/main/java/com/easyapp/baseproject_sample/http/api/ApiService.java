package com.easyapp.baseproject_sample.http.api;


import com.easyapp.baseproject_sample.http.entity.ItemProduct;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 測試用api
 */
public interface ApiService {
    /**
     * 商品列表
     *
     * @param type
     * @param p_name 商品名稱
     * @param page   頁碼
     * @param limit  取數
     * @return
     */
    @POST("production/list")
    @FormUrlEncoded
    Call<ItemProduct> getProductList(@Field("type") String type, @Field("p_name") String p_name, @Field("page") String page, @Field("limit") String limit);

}
