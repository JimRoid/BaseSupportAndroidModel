package com.easyapp.lib.http;

import com.google.gson.annotations.SerializedName;

/**
 * Created by easyapp_jim on 2016/7/11.
 */
public class ItemBase {

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