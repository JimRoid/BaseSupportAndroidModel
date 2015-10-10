package com.easyapp.baseproject.lib.tool.response;

import org.json.JSONObject;

public interface JResponseHandler {
    void Success(int StatusCode, JSONObject response);

    void NoNetwork();
}