package com.easyapp.sample.screen;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyapp.lib.fragment.BaseToolbarFragment;
import com.easyapp.sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteCreate extends BaseToolbarFragment {


    public static NoteCreate instance() {
        return new NoteCreate();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_create, container, false);
    }

}
