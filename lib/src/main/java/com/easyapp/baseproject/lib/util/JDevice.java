package com.easyapp.baseproject.lib.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;

/**
 * Created by easyapp_jim on 15/9/10.
 */
public class JDevice extends ContextWrapper {
    protected Context context;

    public JDevice(Context base) {
        super(base);
        context = base;
    }

    public boolean hasInternet() {
        boolean flag;
        flag = ((ConnectivityManager) context.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
        return flag;
    }
}
