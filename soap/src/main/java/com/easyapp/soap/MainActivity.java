package com.easyapp.soap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RetrofitSoapClient retrofitSoapClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofitSoapClient = RetrofitSoapClient.getInstance();
        retrofitSoapClient.login();
    }
}
