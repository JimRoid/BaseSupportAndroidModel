package com.easyapp.baseproject_sample.http.api;


import com.easyapp.baseproject_sample.http.entity.Photo;
import com.easyapp.baseproject_sample.http.entity.Post;
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

    @GET("/photos?_limit=40")
    Call<List<Photo>> getPhotos();
}
