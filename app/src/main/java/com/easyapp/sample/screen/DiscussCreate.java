package com.easyapp.sample.screen;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.easyapp.image.MultiImageSelectorActivity;
import com.easyapp.lib.fragment.BaseToolbarFragment;
import com.easyapp.lib.menu.MenuView;
import com.easyapp.sample.R;
import com.easyapp.sample.base.BaseAppFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 討論區
 */
public class DiscussCreate extends BaseAppFragment {

    private final int REQUEST_IMAGE = 1001;

    Unbinder unbinder;
    private ArrayList<String> imagePath;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discuss_create, container, false);
        initMenu();
        unbinder = ButterKnife.bind(this, view);
        imagePath = new ArrayList<>();
        return view;
    }

    private void initMenu() {
        MenuView menuView = new MenuView(getContext());
        menuView.setMenuText("說明");
        menuView.setMenuTextColor(R.color.black);
        getLeftMenu().addView(menuView.getMenu());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearMenu();
        unbinder.unbind();
    }

    @OnClick({R.id.card_view, R.id.cvImageSelect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_view:
                break;
            case R.id.cvImageSelect:
                selectImage();
                break;
        }
    }

    private void selectImage() {
        Intent intent = new Intent(getContext(), MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        if (imagePath != null && imagePath.size() > 0) {
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, imagePath);
        }
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                imagePath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Logger.d("imagePath:" + imagePath.size());
            }
        }
    }
}
