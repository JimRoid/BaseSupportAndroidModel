package com.easyapp.sample;

import android.app.Application;

import com.easyapp.database.EasyDB;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MobileApplication extends Application {
    public MobileApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EasyDB.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
