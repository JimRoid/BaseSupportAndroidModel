package com.easyapp.sample.storage;

import com.easyapp.database.Model;
import com.google.gson.annotations.SerializedName;

public class Notes extends Model {

    @Override
    public String getListKey() {
        return "Notes";
    }



    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
