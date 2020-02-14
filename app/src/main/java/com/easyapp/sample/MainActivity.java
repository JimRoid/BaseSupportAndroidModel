package com.easyapp.sample;


import com.easyapp.lib.activity.BaseToolbarActivity;
import com.easyapp.lib.drawer.BaseDrawerMainActivity;
import com.easyapp.sample.loginView.Login;
import com.easyapp.sample.screen.DiscussCreate;
import com.easyapp.sample.screen.DiscussList;
import com.easyapp.sample.screen.DiscussPager;
import com.easyapp.sample.screen.NoteBook;
import com.orhanobut.logger.Logger;

/**
 *
 */
//public class MainActivity extends BaseToolbarActivity {
public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {
//        showLoading();
//        showLoading();
//        showLoading();
//        showLoading();
//        showLoading();
//        showLoading();
//        ReplaceFragment(new Login());
//        ReplaceFragment(NoteBook.instance());
//        ReplaceFragment(new DiscussList());
//        ReplaceFragment(new DiscussPager());
        ReplaceFragment(new DiscussCreate());
    }
}
