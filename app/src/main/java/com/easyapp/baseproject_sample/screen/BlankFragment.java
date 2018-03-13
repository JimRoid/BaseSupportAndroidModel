package com.easyapp.baseproject_sample.screen;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("https://gcs-vimeo.akamaized.net/exp=1520579857~acl=%2A%2F769873483.mp4%2A~hmac=2d948e0896ad1b5a0bb058ee3345dc77c60b8f17312ab0de88c9ef56545230e8/vimeo-prod-skyfire-std-us/01/3238/5/141190368/769873483.mp4");
        Linkify.addLinks(textView, Linkify.WEB_URLS);
        return textView;
    }

}
