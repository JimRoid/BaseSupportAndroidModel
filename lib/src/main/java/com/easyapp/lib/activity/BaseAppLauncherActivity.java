package com.easyapp.lib.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import androidx.annotation.Nullable;

import com.easyapp.lib.R;
import com.easyapp.lib.dialogFactory.SimpleDialog;
import com.easyapp.lib.tool.Utils;


/**
 * 基本的啟動activity
 */

public abstract class BaseAppLauncherActivity extends BaseLoadingActivity {

    protected Handler repeatHandler;
    protected boolean isRunnable = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repeatHandler = new RepeatHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCheckNetworkExist()) {
            if (Utils.isNetworkAvailable(this)) {
                showLauncherView();
            } else {
                SimpleDialog.Description(this, getString(R.string.network_message_title), getString(R.string.network_message_content), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        } else {
            showLauncherView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isRunnable) {
            cancelLauncherView();
        }
    }

    protected boolean isCheckNetworkExist() {
        return true;
    }

    protected int getLauncherDelayTime() {
        return 2000;
    }

    private void showLauncherView() {
        isRunnable = true;
        repeatHandler.postDelayed(runnable, getLauncherDelayTime());
    }

    private void cancelLauncherView() {
        repeatHandler.removeCallbacks(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                startRunnable();
            } catch (Exception e) {
                e.printStackTrace();
                errorRunnable();
            } finally {
                endRunnable();
            }
        }
    };

    abstract protected void startRunnable();

    abstract protected void endRunnable();

    abstract protected void errorRunnable();

    private static class RepeatHandler extends Handler {
        private RepeatHandler() {

        }

        @Override
        public void handleMessage(Message msg) {

        }
    }

}
