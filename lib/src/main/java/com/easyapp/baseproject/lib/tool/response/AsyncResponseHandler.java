package com.easyapp.baseproject.lib.tool.response;

import cz.msebera.android.httpclient.Header;

public interface AsyncResponseHandler {
    void Success(int statusCode, Header[] headers, byte[] responseBody);

    void NoNetwork();

    void Failure(int statusCode, Header[] headers, byte[] responseBody, Throwable error);
}
