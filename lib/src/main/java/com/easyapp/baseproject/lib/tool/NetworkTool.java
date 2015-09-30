package com.easyapp.baseproject.lib.tool;


import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

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


    public void GetRandomChinese(int limit, int n, JResponseHandler responseHandler) {
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

    protected void get(String route, RequestParams params, JResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route" + route);
        Logger("params: " + params);
        asyncHttpClient.get(mContext, route, params, DefaultjsonHttpResponseHandler(responseHandler));
    }

    protected void get(String route, JResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route: " + route);
        asyncHttpClient.get(mContext, route, DefaultjsonHttpResponseHandler(responseHandler));
    }

    protected void POST(String route, RequestParams params, JResponseHandler responseHandler) {
        POST(route, params, false, responseHandler);
    }

    protected void POST(String route, RequestParams params, boolean isLogin, JResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger("route: " + route);
        Logger("params: " + params);
        asyncHttpClient.post(mContext, route, params, DefaultjsonHttpResponseHandler(responseHandler));
    }

    protected void POST(String route, StringEntity stringEntity, AsyncResponseHandler responseHandler) {
        String content_type = "text/plain charset=utf-8";
        POST(route, stringEntity, content_type, responseHandler);
    }

    protected void POST(String route, StringEntity stringEntity, String content_type, AsyncResponseHandler responseHandler) {
        if (!route.startsWith("http"))
            route = baseUrl + route;

        Logger.d(stringEntity.toString());
        asyncHttpClient.post(mContext, route, stringEntity, content_type, DefaultHttpResponseHandler(responseHandler));
    }

    protected AsyncHttpResponseHandler DefaultHttpResponseHandler(final AsyncResponseHandler responseHandler) {
        return new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Logger.d(response);
                responseHandler.Success(statusCode, headers, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String response = new String(responseBody);
                Logger.d(response);
                responseHandler.Fail(statusCode, headers, responseBody);
            }
        };
    }

    protected JsonHttpResponseHandler DefaultjsonHttpResponseHandler(final JResponseHandler responseHandler) {
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

    public interface JResponseHandler {
        void Success(int StatusCode, JSONObject response);

        void Fail(int status, String reason);
    }

    public interface AsyncResponseHandler {
        void Success(int statusCode, Header[] headers, byte[] responseBody);

        void Fail(int statusCode, Header[] headers, byte[] responseBody);
    }

    protected void Logger(String message) {
        if (isShowLog) {
            Logger.d(message);
        }
    }

}
