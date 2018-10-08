package com.easyapp.sample.http.api;


import com.easyapp.easyhttp.model.ResponseBase;
import com.easyapp.sample.http.entity.EntityDiscuss;
import com.easyapp.sample.http.entity.EntityLogin;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 測試用api
 */
public interface ApiService {
    /**
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Call<ResponseBase> register(@Field("account") String account, @Field("password1") String password1, @Field("password2") String password2);


    /**
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Call<EntityLogin> login(@Field("account") String account, @Field("password") String password);


    /**
     * @return
     */
    @POST("discuss/list")
    Call<EntityDiscuss> discussList();


    /**
     * @return
     */
    @POST("discuss/create")
    @Multipart
    Call<ResponseBase> discussCreate(@Header(ApiTool.HeadAuthorization) String token,
                                   @Part("title") RequestBody title,
                                   @Part("content") RequestBody content,
                                   @Part List<MultipartBody.Part> pic);

}
