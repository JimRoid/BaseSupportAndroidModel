package com.easyapp.app_http;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @Multipart
    @POST("upload-app")
    Call<JsonObject> uploadImage(@Part MultipartBody.Part file);

    @Multipart
    @POST("upload-app")
    Call<JsonObject> uploadImage(@Part List<MultipartBody.Part> file);
}
