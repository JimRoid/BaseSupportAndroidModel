package com.easyapp.sample.storage;

import com.easyapp.database.SingleModel;
import com.google.gson.annotations.SerializedName;

public class Note extends SingleModel<Note> {

    @Override
    public String getSingleKey() {
        return "Note";
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
