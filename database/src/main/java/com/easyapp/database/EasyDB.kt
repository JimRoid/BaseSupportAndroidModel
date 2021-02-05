package com.easyapp.database

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import java.lang.ref.WeakReference
import java.util.*

/**
 * 以 key value 進行資料儲存處理
 * 可儲存成列表
 */
object EasyDB {
    private const val LOGIN = "LOGIN"
    private const val TOKEN = "TOKEN"
    private var applicationWeakReference: WeakReference<Application>? = null
    private var sharedPreferences: SharedPreferences? = null

    @JvmStatic
    fun init(application: Application) {
        applicationWeakReference = WeakReference(application)
        val packageName = context!!.packageName
        sharedPreferences = context!!.getSharedPreferences(packageName, Context.MODE_PRIVATE)
    }

    val context: Context?
        get() = applicationWeakReference!!.get()

    fun clear() {
        sharedPreferences!!.edit().clear().apply()
    }

    /**
     * 建立隨機的uuid
     *
     * @return uuid
     */
    @JvmStatic
    fun RandUUID(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }

    /**
     * 記錄登入的token
     *
     * @param token
     */
    fun Login(token: String?) {
        if (sharedPreferences != null) {
            sharedPreferences!!.edit().putBoolean(LOGIN, true).apply()
            sharedPreferences!!.edit().putString(TOKEN, token).apply()
        }
    }

    fun Logout() {
        sharedPreferences!!.edit().putBoolean(LOGIN, false).apply()
        sharedPreferences!!.edit().putString(TOKEN, "").apply()
    }

    @JvmStatic
    val token: String
        get() = sharedPreferences!!.getString(TOKEN, "")
    val isLogin: Boolean
        get() = sharedPreferences!!.getBoolean(LOGIN, false)

    @JvmStatic
    fun putString(key: String?, value: String?): Boolean {
        return sharedPreferences!!.edit().putString(key, value).commit()
    }

    fun putBoolean(key: String?, value: Boolean): Boolean {
        return sharedPreferences!!.edit().putBoolean(key, value).commit()
    }

    fun putInt(key: String?, value: Int): Boolean {
        return sharedPreferences!!.edit().putInt(key, value).commit()
    }

    fun putLong(key: String?, value: Long): Boolean {
        return sharedPreferences!!.edit().putLong(key, value).commit()
    }

    fun putFloat(key: String?, value: Float): Boolean {
        return sharedPreferences!!.edit().putFloat(key, value).commit()
    }

   fun getString(key: String?, value: String?): String {
        return sharedPreferences!!.getString(key, value)
    }

    @JvmStatic
    fun getString(key: String?): String {
        return getString(key, "")
    }

    fun getBoolean(key: String?, value: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, value)
    }

    fun getBoolean(key: String?): Boolean {
        return getBoolean(key, false)
    }

    fun getInt(key: String?, value: Int): Int {
        return sharedPreferences!!.getInt(key, value)
    }

    fun getInt(key: String?): Int {
        return getInt(key, 0)
    }

    fun getLong(key: String?, value: Long): Long {
        return sharedPreferences!!.getLong(key, value)
    }

    fun getLong(key: String?): Long {
        return getLong(key, 0)
    }

    fun getFloat(key: String?, value: Float): Float {
        return sharedPreferences!!.getFloat(key, value)
    }

    fun getFloat(key: String?): Float {
        return getFloat(key, 0f)
    }

    @JvmStatic
    fun putList(key: String?, strings: ArrayList<String>) {
        val array = strings.toTypedArray()
        sharedPreferences!!.edit().putString(key, TextUtils.join("‚‗‚", array)).apply()
    }

    @JvmStatic
    fun getList(key: String?): ArrayList<String> {
        val split = TextUtils.split(sharedPreferences!!.getString(key, ""), "‚‗‚")
        val strings = ArrayList<String>()
        if (split.size > 0) {
            strings.addAll(ArrayList(Arrays.asList(*split)))
        }
        return strings
    }
}