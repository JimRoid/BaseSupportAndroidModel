package com.easyapp.baseproject_sample.screen;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.easyapp.baseproject_sample.R;
import com.easyapp.baseproject_sample.draweractivity.DrawerViewActivity;
import com.easyapp.baseproject_sample.loginView.LoginActivity;
import com.easyapp.baseproject_sample.tabactivity.TabActivity;
import com.easyapp.lib.base.fragment.BaseToolbarFragment;
import com.easyapp.lib.tool.OpenData;
import com.easyapp.lib.widget.MenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends BaseToolbarFragment {

    @BindView(R.id.bt_list)
    Button btList;
    @BindView(R.id.bt_tab)
    Button btTab;
    @BindView(R.id.bt_drawerView)
    Button btDrawerView;
    @BindView(R.id.bt_staggered_list)
    Button btStaggeredList;
    @BindView(R.id.dot_viewpager)
    Button dotViewpager;
    @BindView(R.id.bt_loginView)
    Button btLoginView;
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setToolbarCallback(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sample, container, false);
        showToolbar();
        ButterKnife.bind(this, view);
        MenuView menuView = new MenuView();
        menuView.Builder(getActivity());
        menuView.setMenuText("儲存");
        menuView.setMenuTextColor(R.color.white);
        setRight(menuView.getMenu());
        return view;
    }


    protected void AddToList() {
        AddFragment(new PostsList());
    }

    protected void AddToList2() {
        AddFragment(new PostsStaggeredList());
    }

    protected void AddToDotView2() {
        AddFragment(new FragmentViewPageCircle());
    }

    protected void OpenTabActivity() {
        OpenData.StartActivity(getActivity(), TabActivity.class);
    }

    protected void OpenDrawerViewActivity() {
        OpenData.StartActivity(getActivity(), DrawerViewActivity.class);
    }

    @OnClick({R.id.bt_loginView, R.id.bt_list, R.id.bt_tab, R.id.bt_drawerView, R.id.bt_staggered_list, R.id.dot_viewpager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_list:
                AddToList();
                break;
            case R.id.bt_tab:
                OpenTabActivity();
                break;
            case R.id.bt_drawerView:
                OpenDrawerViewActivity();
                break;
            case R.id.bt_staggered_list:
                AddToList2();
                break;
            case R.id.dot_viewpager:
                AddToDotView2();
                break;
            case R.id.bt_loginView:
                OpenData.StartActivity(getActivity(), LoginActivity.class);
                break;
        }
    }


}
