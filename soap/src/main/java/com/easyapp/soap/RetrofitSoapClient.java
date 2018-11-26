package com.easyapp.soap;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitSoapClient {

    String path = "http://60.249.120.228/aPMBSTDLOD01.aspx?wsdl";

    //public ZYDApiService service;
    public SoapServices service;
    public String baseUrl = "http://60.249.120.228/";

    private RetrofitSoapClient() {
        //okhttp log : 包含header、body数据
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okhttp client
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        //retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())      //添加 String类型[ Scalars (primitives, boxed, and String)] 转换器
                .addConverterFactory(JaxbConverterFactory.create())    //添加 xml数据类型 bean-<xml
//                .addConverterFactory(GsonConverterFactory.create())         //添加 json数据类型 bean->json
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        service = retrofit.create(SoapServices.class);
    }

    private static RetrofitSoapClient INSTANCE = null;

    //获取单例
    public static RetrofitSoapClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitSoapClient();
        }
        return INSTANCE;
    }

    public void login() {
        String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cek=\"CEK\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <cek:PMBSTDLOD01.Execute>\n" +
                "         <cek:Studentpid>" + "J105401560" + "</cek:Studentpid>\n" +
                "         <cek:Studentpwd>" + "0000" + "</cek:Studentpwd>\n" +
                "      </cek:PMBSTDLOD01.Execute>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        service.login(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("output", response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
