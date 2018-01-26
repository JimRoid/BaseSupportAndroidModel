package com.easyapp.baseproject_sample.http.entity;

import java.util.List;

/**
 * Created by easyapp_jim on 2016/7/11.
 */
public class ItemSchool {


    /**
     * status : 200
     * message : success
     * data : [{"id":"0df02f7d-d672-11e7-aeeb-04011089","permission":"1","name":"討論區","picture":"https://test2.easyapp.com.tw/easy-school/data/admin/5a21136c7d40e.jpg","phone":"0972605002","email":"jim@easyapp.com.tw"}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0df02f7d-d672-11e7-aeeb-04011089
         * permission : 1
         * name : 討論區
         * picture : https://test2.easyapp.com.tw/easy-school/data/admin/5a21136c7d40e.jpg
         * phone : 0972605002
         * email : jim@easyapp.com.tw
         */

        private String id;
        private String permission;
        private String name;
        private String picture;
        private String phone;
        private String email;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
