package com.easyapp.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.easyapp.lib.fragment.BaseToolbarFragment;
import com.easyapp.sample.http.api.ApiTool;

/**
 * base fragment
 */
public abstract class BaseAppFragment extends BaseToolbarFragment {

    protected ApiTool apiTool;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiTool = new ApiTool(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated();
        onRequestData();
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onRequestData() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onViewCreated();
            onRequestData();
        }
    }


}
