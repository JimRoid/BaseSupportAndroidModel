package com.easyapp.lib.http.model;

import com.google.gson.annotations.SerializedName;

/**
 * 基本的response 回傳
 */
public class ResponseBase {

    /**
     * status : 200
     * message : 搜尋成功
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}