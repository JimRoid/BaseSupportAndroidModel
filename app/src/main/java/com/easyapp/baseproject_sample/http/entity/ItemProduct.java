package com.easyapp.baseproject_sample.http.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by easyapp_jim on 2016/7/11.
 */
public class ItemProduct {


    /**
     * status : 200
     * message : 搜尋成功
     * data : {"total":5,"content":[{"number":1,"p_id":"14b25c0e-4412-11e6-b4e9-04011089","name":"家樂福禮券 1000","period":"1","type":"票券預付卡","s_pic":"http://139.59.246.193/treasure/data/production/14b25c0e-4412-11e6-b4e9-04011089.jpg","is_main":"0","coin_demand":"500","coin_total":"0"},{"number":2,"p_id":"1875ef6a-4411-11e6-b4e9-04011089","name":"【 ARTBOX 】超次元29吋輕量PC鏡面鋁框行李箱 ART802-29玫瑰金","period":"1","type":"時尚精品","s_pic":"http://139.59.246.193/treasure/data/production/1875ef6a-4411-11e6-b4e9-04011089.jpg","is_main":"0","coin_demand":"500","coin_total":"0"},{"number":3,"p_id":"75eca931-4411-11e6-b4e9-04011089","name":"【Panasonic 國際牌】白金負離子奈米水離子折疊吹風機 EH-NA45 不挑色","period":"1","type":"數位影音","s_pic":"http://139.59.246.193/treasure/data/production/75eca931-4411-11e6-b4e9-04011089.png","is_main":"0","coin_demand":"2000","coin_total":"0"},{"number":4,"p_id":"8214820c-4411-11e6-b4e9-04011089","name":"【dyson】V6 mattress HH07 無線除塵蹣機(白綠色)","period":"1","type":"數位影音","s_pic":"http://139.59.246.193/treasure/data/production/8214820c-4411-11e6-b4e9-04011089.jpg","is_main":"0","coin_demand":"2000","coin_total":"0"},{"number":5,"p_id":"b494219b-4400-11e6-b4e9-04011089","name":"【Apple】iPhone 6s 64G 不挑色","period":"1","type":"電子產品","s_pic":"http://139.59.246.193/treasure/data/production/b494219b-4400-11e6-b4e9-04011089.jpg","is_main":"1","coin_demand":"1000","coin_total":"0"}]}
     */

    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    /**
     * total : 5
     * content : [{"number":1,"p_id":"14b25c0e-4412-11e6-b4e9-04011089","name":"家樂福禮券 1000","period":"1","type":"票券預付卡","s_pic":"http://139.59.246.193/treasure/data/production/14b25c0e-4412-11e6-b4e9-04011089.jpg","is_main":"0","coin_demand":"500","coin_total":"0"},{"number":2,"p_id":"1875ef6a-4411-11e6-b4e9-04011089","name":"【 ARTBOX 】超次元29吋輕量PC鏡面鋁框行李箱 ART802-29玫瑰金","period":"1","type":"時尚精品","s_pic":"http://139.59.246.193/treasure/data/production/1875ef6a-4411-11e6-b4e9-04011089.jpg","is_main":"0","coin_demand":"500","coin_total":"0"},{"number":3,"p_id":"75eca931-4411-11e6-b4e9-04011089","name":"【Panasonic 國際牌】白金負離子奈米水離子折疊吹風機 EH-NA45 不挑色","period":"1","type":"數位影音","s_pic":"http://139.59.246.193/treasure/data/production/75eca931-4411-11e6-b4e9-04011089.png","is_main":"0","coin_demand":"2000","coin_total":"0"},{"number":4,"p_id":"8214820c-4411-11e6-b4e9-04011089","name":"【dyson】V6 mattress HH07 無線除塵蹣機(白綠色)","period":"1","type":"數位影音","s_pic":"http://139.59.246.193/treasure/data/production/8214820c-4411-11e6-b4e9-04011089.jpg","is_main":"0","coin_demand":"2000","coin_total":"0"},{"number":5,"p_id":"b494219b-4400-11e6-b4e9-04011089","name":"【Apple】iPhone 6s 64G 不挑色","period":"1","type":"電子產品","s_pic":"http://139.59.246.193/treasure/data/production/b494219b-4400-11e6-b4e9-04011089.jpg","is_main":"1","coin_demand":"1000","coin_total":"0"}]
     */

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
        @SerializedName("total")
        private int total;
        /**
         * number : 1
         * p_id : 14b25c0e-4412-11e6-b4e9-04011089
         * name : 家樂福禮券 1000
         * period : 1
         * type : 票券預付卡
         * s_pic : http://139.59.246.193/treasure/data/production/14b25c0e-4412-11e6-b4e9-04011089.jpg
         * is_main : 0
         * coin_demand : 500
         * coin_total : 0
         */

        private List<ContentBean> content;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            @SerializedName("number")
            private int number;
            @SerializedName("p_id")
            private String p_id;
            @SerializedName("name")
            private String name;
            @SerializedName("period")
            private String period;
            @SerializedName("type")
            private String type;
            @SerializedName("s_pic")
            private String s_pic;
            @SerializedName("is_main")
            private String is_main;
            @SerializedName("coin_demand")
            private String coin_demand;
            @SerializedName("coin_total")
            private String coin_total;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getP_id() {
                return p_id;
            }

            public void setP_id(String p_id) {
                this.p_id = p_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getS_pic() {
                return s_pic;
            }

            public void setS_pic(String s_pic) {
                this.s_pic = s_pic;
            }

            public String getIs_main() {
                return is_main;
            }

            public void setIs_main(String is_main) {
                this.is_main = is_main;
            }

            public String getCoin_demand() {
                return coin_demand;
            }

            public void setCoin_demand(String coin_demand) {
                this.coin_demand = coin_demand;
            }

            public String getCoin_total() {
                return coin_total;
            }

            public void setCoin_total(String coin_total) {
                this.coin_total = coin_total;
            }
        }
    }
}
