package com.easyapp.apitest.api;


import com.easyapp.apitest.entity.Photo;
import com.easyapp.apitest.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 測試用api
 */
public interface ApiService {
    /**
     * 取回測試用資料
     *
     * @return
     */
    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/photos")
    Call<List<Photo>> getPhotos();
}
