package com.easyapp.baseproject.lib.tool;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.easyapp.baseproject.lib.R;
import com.easyapp.baseproject.lib.tool.response.ResponseHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by easyapp_jim on 15/3/19.
 */
public class NetworkTool {
    public static final String SUCCESS_CODE = "status";
    protected Activity activity;
    protected static AsyncHttpClient asyncHttpClient;
    protected boolean isShowLog = true;

    protected String baseUrl = "";
    private AlertDialog alertDialog;

    static {
        asyncHttpClient = new AsyncHttpClient();
    }


    public NetworkTool(Activity context, String baseUrl) {
        activity = context;
        this.baseUrl = baseUrl;

        alertDialog = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setTitle(R.string.network_message_title)
                .setMessage(R.string.network_message_content)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }

    public void setDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }


    public void GetRandomChinese(int limit, int n, ResponseHandler responseHandler) {
        String route = "http://more.handlino.com/sentences.json?limit=" + limit + "&n=" + n;
        GET(route, responseHandler);
    }

    protected void removeAllHeaders() {
        asyncHttpClient.removeAllHeaders();
    }

    protected void removeHeaders(String header) {
        asyncHttpClient.removeHeader(header);
    }

    protected void addHeader(String header, String value) {
        asyncHttpClient.addHeader(header, value);
    }

    protected void GET(String route, RequestParams params, ResponseHandler responseHandler) {
        if (!isNetworkConnected(activity)) {
            responseHandler.NoNetwork();
            showNetworkCheck();
            return;
        }

        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route" + route);
        Logger("params: " + params);
        asyncHttpClient.get(activity, route, params, Default_jsonHttpResponseHandler(responseHandler));
    }

    protected void GET(String route, ResponseHandler responseHandler) {
        if (!isNetworkConnected(activity)) {
            responseHandler.NoNetwork();
            showNetworkCheck();
            return;
        }

        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route: " + route);
        asyncHttpClient.get(activity, route, Default_jsonHttpResponseHandler(responseHandler));
    }

    protected void POST(String route, RequestParams params, ResponseHandler responseHandler) {
        POST(route, params, false, responseHandler);
    }

    protected void POST(String route, RequestParams params, boolean isLogin, ResponseHandler responseHandler) {
        if (!isNetworkConnected(activity)) {
            responseHandler.NoNetwork();
            showNetworkCheck();
            return;
        }

        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route: " + route);
        Logger("params: " + params);
        asyncHttpClient.post(activity, route, params, Default_jsonHttpResponseHandler(responseHandler));
    }

    protected void POST(String route, StringEntity stringEntity, ResponseHandler responseHandler) {
        String content_type = "text/plain charset=utf-8";
        POST(route, stringEntity, content_type, responseHandler);
    }

    protected void POST(String route, StringEntity stringEntity, String content_type, ResponseHandler responseHandler) {
        if (!isNetworkConnected(activity)) {
            responseHandler.NoNetwork();
            showNetworkCheck();
            return;
        }

        if (!route.startsWith("http"))
            route = baseUrl + route;

        asyncHttpClient.post(activity, route, stringEntity, content_type, DefaultHttpResponseHandler(responseHandler));
    }

    protected AsyncHttpResponseHandler DefaultHttpResponseHandler(final ResponseHandler responseHandler) {
        return new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                responseHandler.Success(statusCode, headers, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (responseBody.length > 0) {
                    String response = new String(responseBody);
                    Logger.d(response);
                    responseHandler.Failure(statusCode, headers, responseBody, error);
                }
            }
        };
    }

    protected JsonHttpResponseHandler Default_jsonHttpResponseHandler(final ResponseHandler responseHandler) {
        return new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Logger.d(response.toString());
                responseHandler.Success(statusCode, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                responseHandler.Failure(statusCode, headers, responseString.getBytes(), throwable);
            }
        };
    }


    protected void Logger(String message) {
        if (isShowLog) {
            Logger.d(message);
        }
    }

    protected void showNetworkCheck() {
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    public boolean isWifi() {
        //WIFI
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }


        return false;
    }

    public boolean isMobile() {
        //MOBILE
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getActiveNetworkInfo();

        if (network == null || !network.isConnected()) {
            return false;
        } else {
            return network.isAvailable();
        }
    }

}
