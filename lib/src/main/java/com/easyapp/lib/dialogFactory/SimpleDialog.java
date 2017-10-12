package com.easyapp.lib.dialogFactory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.easyapp.lib.R;

public class SimpleDialog {

    /**
     * 敘述一個dialog
     *
     * @param context
     * @param title
     * @param message
     */
    public static void Description(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.understand, null)
                .show();
    }

    public static void Description(Context context, String title, String message, DialogInterface.OnClickListener clickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setNegativeButton(R.string.understand, clickListener)
                .show();
    }


    /**
     * 帶有描述的dialog 視窗
     *
     * @param context
     * @param title
     * @param message
     * @param acceptClickListener
     * @param cancelClickListener
     */
    public static void Description(Context context, String title, String message, DialogInterface.OnClickListener acceptClickListener, DialogInterface.OnClickListener cancelClickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.accept, acceptClickListener)
                .setNeutralButton(R.string.cancel, cancelClickListener)
                .show();
    }
}