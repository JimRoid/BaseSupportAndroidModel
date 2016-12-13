package com.easyapp.lib.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.easyapp.lib.R;

public class BaseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
    }
}
