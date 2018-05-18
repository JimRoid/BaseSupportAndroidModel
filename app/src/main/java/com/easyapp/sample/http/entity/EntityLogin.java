package com.easyapp.sample.http.entity;

public class EntityLogin {

    /**
     * status : 200
     * message : 登入成功
     * data : {"HTTP_AUTHORIZATION":"676fc475422c72274072cd1e724eea12"}
     */

    private int status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * HTTP_AUTHORIZATION : 676fc475422c72274072cd1e724eea12
         */

        private String HTTP_AUTHORIZATION;

        public String getHTTP_AUTHORIZATION() {
            return HTTP_AUTHORIZATION;
        }

        public void setHTTP_AUTHORIZATION(String HTTP_AUTHORIZATION) {
            this.HTTP_AUTHORIZATION = HTTP_AUTHORIZATION;
        }
    }
}
