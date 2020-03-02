package com.easyapp.lib.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.easyapp.lib.fragment.Loading;
import com.easyapp.lib.callback.iLoading;

/**
 * @author jim
 */
public abstract class BaseLoadingActivity extends AppCompatActivity implements iLoading {
    protected Toast toast;
    protected Loading loading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading = Loading.newInstance();
    }


    @Override
    public void showLoading() {
        if (loading.isAdded() && loading.isVisible() && loading.isRemoving()) {
            return;
        }
        if (!loading.isAdded()
                && !loading.isVisible()
                && !loading.isRemoving()) {
            loading.show(getSupportFragmentManager(), "load");
        }
    }

    @Override
    public void cancelLoading() {
        if (loading.isAdded()) {
            loading.dismiss();
        }
    }


    /**
     * 可關閉的 Toast
     *
     * @param content
     */
    protected void showToast(CharSequence content) {
        showToast(content, false);
    }

    /**
     * 可關閉的 Toast
     *
     * @param content
     */
    protected void showToast(CharSequence content, boolean isLong) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, content, isLong ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * 隱藏鍵盤
     */
    protected void hideKeyboard() {
        if (getCurrentFocus() == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard();
        }
        return super.dispatchTouchEvent(ev);
    }

}
