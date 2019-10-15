package com.easyapp.database;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 基本的database model
 */
public abstract class Model<T extends Model> implements Serializable {

    public abstract String getListKey();

    /**
     * 唯一值不可操作
     */
    @SerializedName("uniqueId")
    private String uniqueId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getGson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    final public void removeAll() {
        EasyDB.putList(getListKey(), new ArrayList<String>());
    }


    /**
     * 取回所有項目
     */
    final public ArrayList<T> getList() {
        ArrayList<String> arrayList = EasyDB.getList(getListKey());
        ArrayList<T> items = new ArrayList<>();
        Gson gson = new Gson();

        for (int i = 0; i < arrayList.size(); i++) {
            Class classOfT = getClass();
            T t = gson.fromJson(arrayList.get(i), (Type) classOfT);
            items.add(t);
        }
        return items;
    }

    public T get(String value) {
        T t = null;
        Gson gson = new Gson();
        ArrayList<String> arrayList = EasyDB.getList(getListKey());
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).contains(value)) {
                Class classOfT = getClass();
                t = gson.fromJson(arrayList.get(i), (Type) classOfT);
                return t;
            }
        }
        return t;
    }

    public ArrayList<T> select(String value) {
        ArrayList<T> list = new ArrayList<>();
        Gson gson = new Gson();
        ArrayList<String> arrayList = EasyDB.getList(getListKey());
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).contains(value)) {
                Class classOfT = getClass();
                T t = gson.fromJson(arrayList.get(i), (Type) classOfT);
                list.add(t);
            }
        }
        return list;
    }


    /**
     * 從list 刪除項目
     */
    public void delete() {
        ArrayList<String> arrayList = EasyDB.getList(getListKey());
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size(); i++) {
            Model item = gson.fromJson(arrayList.get(i), this.getClass());
            if (item.getUniqueId().equals(this.getUniqueId())) {
                arrayList.remove(i);
                EasyDB.putList(getListKey(), arrayList);
            }
        }
    }

    /**
     * 儲存項目到list
     */
    final public void save() {
        ArrayList<String> arrayList = EasyDB.getList(getListKey());
        this.setUniqueId(EasyDB.RandUUID());
        arrayList.add(this.getGson());
        EasyDB.putList(getListKey(), arrayList);
    }

    /**
     * 更新單一項目儲存到list
     */
    final public void update() {
        ArrayList<String> arrayList = EasyDB.getList(getListKey());
        int position;
        Gson gson = new Gson();
        for (int i = 0; i < arrayList.size(); i++) {
            Model item = gson.fromJson(arrayList.get(i), this.getClass());
            if (item.getUniqueId().equals(this.getUniqueId())) {
                position = i;
                arrayList.set(position, this.getGson());
                EasyDB.putList(getListKey(), arrayList);
                return;
            }
        }
    }
}