package com.easyapp.baseproject.lib.tool;


import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by easyapp_jim on 15/3/19.
 */
public class NetworkTool {
    public static final String SUCCESS_CODE = "status";
    protected Context mContext;
    protected static AsyncHttpClient asyncHttpClient;
    protected boolean isShowLog = true;


    protected String baseUrl = "";

    static {
        asyncHttpClient = new AsyncHttpClient();
    }


    public NetworkTool(Context context, String baseUrl) {
        mContext = context;
        this.baseUrl = baseUrl;
    }


    public void GetRandomChinese(int limit, int n, ResponseHandler responseHandler) {
        String route = "http://more.handlino.com/sentences.json?limit=" + limit + "&n=" + n;
        get(route, responseHandler);
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

    protected void get(String route, RequestParams params, ResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route" + route);
        Logger("params: " + params);
        asyncHttpClient.get(mContext, route, params, DefaultjsonHttpResponseHandler(responseHandler));
    }

    protected void get(String route, ResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route: " + route);
        asyncHttpClient.get(mContext, route, DefaultjsonHttpResponseHandler(responseHandler));
    }

    protected void post(String route, RequestParams params, ResponseHandler responseHandler) {
        post(route, params, false, responseHandler);
    }

    protected void post(String route, RequestParams params, boolean isLogin, ResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route: " + route);
        Logger("params: " + params);
        asyncHttpClient.post(mContext, route, params, DefaultjsonHttpResponseHandler(responseHandler));
    }


    protected JsonHttpResponseHandler DefaultjsonHttpResponseHandler(final ResponseHandler responseHandler) {
        JsonHttpResponseHandler jsonHttpResponseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Logger.d(response.toString());
                responseHandler.Success(statusCode, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(mContext, "更新失敗請檢查網路", Toast.LENGTH_SHORT).show();
                if (null != errorResponse) {
                    responseHandler.Fail(statusCode, errorResponse.toString());
                } else {
                    responseHandler.Fail(statusCode, "");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(mContext, "更新失敗請檢查網路", Toast.LENGTH_SHORT).show();
                responseHandler.Fail(statusCode, "");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(mContext, "更新失敗請檢查網路", Toast.LENGTH_SHORT).show();
                responseHandler.Fail(statusCode, responseString);
            }
        };
        return jsonHttpResponseHandler;
    }

    public interface ResponseHandler {
        void Success(int StatusCode, JSONObject response);

        void Fail(int status, String reason);
    }

    protected void Logger(String message) {
        if (isShowLog) {
            Logger.d(message);
        }
    }

}
