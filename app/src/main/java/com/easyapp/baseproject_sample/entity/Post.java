package com.easyapp.baseproject_sample.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by easyapp_jim on 2016/6/13.
 */
public class Post {

    /**
     * userId : 1
     * id : 1
     * title : sunt aut facere repellat provident occaecati excepturi optio reprehenderit
     * body : quia et suscipit
     suscipit recusandae consequuntur expedita et cum
     reprehenderit molestiae ut ut quas totam
     nostrum rerum est autem sunt rem eveniet architecto
     */

    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
