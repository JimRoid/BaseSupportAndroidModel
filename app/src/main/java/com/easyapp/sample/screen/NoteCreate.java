package com.easyapp.sample.screen;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.easyapp.lib.backpressHelper.BackHandlerHelper;
import com.easyapp.lib.fragment.BaseToolbarFragment;
import com.easyapp.sample.R;
import com.easyapp.sample.base.BaseAppFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 */
public class NoteCreate extends BaseAppFragment {

    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.btSend)
    Button btSend;
    private boolean backHandled = true;
    Unbinder unbinder;
    public static NoteCreate instance() {
        return new NoteCreate();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_create, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public boolean onBackPressed() {
        if (backHandled) {
            Logger.d("onBackPressed");
            return true;
        } else {
            return BackHandlerHelper.handleBackPress(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btSend)
    public void onViewClicked() {
        backHandled = false;
        getActivity().onBackPressed();
    }
}
