package com.easyapp.database;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 基本的database model
 * 只會存取單一的資料進行更新
 * 並不會出現列表
 */

public abstract class SingleModel {

    public abstract String getSingleKey();

    public SingleModel() {
        super();
    }

    public String getGson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

//    public static <T extends SingleModel> T restore(Context context) {
//        T t = null;
//        EasyDB easyDB = new EasyDB(context);
//        String value = easyDB.getStringValue(t.getSingleKey());
//        Gson gson = new Gson();
//        Class classOfT = t.getClass();
//        t = gson.fromJson(value, (Type) classOfT);
//        return t;
//    }

    final public <T extends SingleModel> T getStore() {
        String value = EasyDB.getString(getSingleKey());
        Gson gson = new Gson();
        Class classOfT = getClass();
        gson.fromJson(value, (Type) classOfT);
        return gson.fromJson(value, (Type) classOfT);
    }

    final public boolean save() {
        String value = getGson();
        return EasyDB.putString(getSingleKey(), value);
    }

    final public boolean update() {
        String value = getGson();
        return EasyDB.putString(getSingleKey(), value);
    }

    final public boolean clear() {
        return EasyDB.putString(getSingleKey(), "");
    }
}
