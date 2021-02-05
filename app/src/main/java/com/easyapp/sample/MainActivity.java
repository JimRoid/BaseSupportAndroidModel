package com.easyapp.sample;


import com.easyapp.lib.activity.BaseToolbarActivity;
import com.easyapp.sample.screen.DiscussCreate;
import com.easyapp.sample.screen.DiscussList;

/**
 *
 */
public class MainActivity extends BaseToolbarActivity {
//public class MainActivity extends BaseDrawerMainActivity {

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
//        replaceFragment(DiscussList.getInstance());
//        ReplaceFragment(new DiscussPager(/));
        replaceFragment(new DiscussCreate());
//        replaceFragment(BlankFragmentText.getInstance(0));
    }
}
