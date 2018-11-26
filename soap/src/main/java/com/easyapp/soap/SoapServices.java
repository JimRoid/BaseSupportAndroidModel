package com.easyapp.soap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SoapServices {

    @Headers({"Content-Type:application/soap+xml;charset=UTF-8"})
    @POST("apmbstdlod01.aspx?wsdl")
    Call<ResponseBody> login(@Body String s);


    @Headers({"Content-Type:application/soap+xml;charset=UTF-8"})
    @POST("apmbstdbult02.aspx?wsdl")
    Call<ResponseBody> news(@Body String s);


}
