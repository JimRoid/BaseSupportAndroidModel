package com.easyapp.sample;


import com.easyapp.lib.drawer.BaseDrawerMainActivity;
import com.easyapp.sample.screen.DiscussList;

/**
 *
 */
//public class MainActivity extends BaseToolbarActivity {
public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {

//        ReplaceFragment(NoteBook.instance());
        ReplaceFragment(new DiscussList());
//        ReplaceFragment(new DiscussPager());
//        ReplaceFragment(new DiscussCreate());
    }
}
