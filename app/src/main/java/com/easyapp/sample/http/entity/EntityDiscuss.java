package com.easyapp.sample.http.entity;

import java.util.List;

public class EntityDiscuss {


    /**
     * status : 200
     * message : 搜尋成功
     * data : [{"id":"2","userId":"1","title":"標題","content":"內文","picture":["https://test2.easyapp.com.tw/easy-chat/data/discuss/5afe8f2985914.jpg","https://test2.easyapp.com.tw/easy-chat/data/discuss/5afe8f2985995.jpg"],"discussId":"","createDate":"2018-05-18 16:30:33"}]
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
         * id : 2
         * userId : 1
         * title : 標題
         * content : 內文
         * picture : ["https://test2.easyapp.com.tw/easy-chat/data/discuss/5afe8f2985914.jpg","https://test2.easyapp.com.tw/easy-chat/data/discuss/5afe8f2985995.jpg"]
         * discussId :
         * createDate : 2018-05-18 16:30:33
         */

        private String id;
        private String userId;
        private String title;
        private String content;
        private String discussId;
        private String createDate;
        private List<String> picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

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

        public String getDiscussId() {
            return discussId;
        }

        public void setDiscussId(String discussId) {
            this.discussId = discussId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<String> getPicture() {
            return picture;
        }

        public void setPicture(List<String> picture) {
            this.picture = picture;
        }
    }
}
