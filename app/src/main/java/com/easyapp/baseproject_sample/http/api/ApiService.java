package com.easyapp.baseproject_sample.http.api;


import com.easyapp.baseproject_sample.http.entity.ItemSchool;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 測試用api
 */
public interface ApiService {
    /**
     * 商品列表
     *
     * @return
     */
    @GET("main/school")
    Call<ItemSchool> getSchool();

}
