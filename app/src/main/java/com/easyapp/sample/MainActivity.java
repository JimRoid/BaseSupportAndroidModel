package com.easyapp.sample;


import com.easyapp.lib.activity.BaseMainActivity;
import com.easyapp.lib.drawer.BaseDrawerMainActivity;
import com.easyapp.sample.screen.DiscussCreate;
import com.easyapp.sample.screen.DiscussList;
import com.easyapp.sample.screen.DiscussPager;
import com.easyapp.sample.screen.NoteBook;

/**
 *
 */
//public class MainActivity extends BaseMainActivity {
public class MainActivity extends BaseDrawerMainActivity {

    @Override
    protected void initial() {

        ReplaceFragment(NoteBook.instance());
//        ReplaceFragment(new DiscussList());
//        ReplaceFragment(new DiscussPager());
//        ReplaceFragment(new DiscussCreate());
    }
}
