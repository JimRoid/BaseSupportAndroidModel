package com.easyapp.baseproject_sample;

import com.google.gson.annotations.SerializedName;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class ItemNews {

    /**
     * number : 1
     * id : edae72fb-0aba-11e6-9f03-122186ee
     * name : 基隆數位學院
     * latitude : 25.1284585
     * longitude : 121.75334539999994
     * address : 基隆市信義區信一路57號2樓
     * phone : 02-24280933
     */

    @SerializedName("number")
    private int number;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
