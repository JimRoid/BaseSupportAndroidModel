package com.easyapp.lib.widget.viewPager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.easyapp.lib.R;
import com.easyapp.lib.glide.GlideImageLoader;
import com.easyapp.lib.tool.OpenData;
import com.easyapp.lib.touchView.TouchViewActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collection;

public class EasyImageViewPagerAdapter extends PagerAdapter {

    private ArrayList<String> urls;
    private LayoutInflater inflater;
    private Context context;

    private OnItemClickListener itemClickListener;

    private int layout = R.layout.layout_image_view;
    private int imageViewLayoutId = R.id.ivPicture;
    private int layoutLoad = R.id.progressBar;

    public static void initial(Activity activity,
                               Collection<? extends String> collection,
                               ViewPager viewPager,
                               TabLayout tabLayout) {
        EasyImageViewPagerAdapter pagerAdapter = new EasyImageViewPagerAdapter(activity);
        pagerAdapter.setData(collection);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public EasyImageViewPagerAdapter(final Activity activity) {
        super();
        urls = new ArrayList<>();
        context = activity;
        inflater = LayoutInflater.from(activity);
        itemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                TouchViewActivity.startActivity(activity, position, "http", urls, false);
            }
        };
    }

    public void initView(ViewPager viewPager, TabLayout tabLayout) {
        viewPager.setAdapter(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setData(final Collection<? extends String> collection) {
        urls.clear();
        urls.addAll(collection);
    }

    /**
     * To be called by onStop
     * Clean the memory
     */
    public void release() {
        urls.clear();
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return urls.size();
    }

    @NonNull
    public View instantiateItem(@NonNull ViewGroup container, final int position) {
        int rootLayout = layout;
        View currentView = inflater.inflate(rootLayout, container, false);
        final ImageView imageView = currentView.findViewById(imageViewLayoutId);
        final ProgressBar progressView = currentView.findViewById(layoutLoad);

        new GlideImageLoader(imageView, progressView).load(urls.get(position));

        currentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position);
            }
        });

        container.addView(currentView);
        return currentView;
    }

    public void setItemClickListener(OnItemClickListener clickListener) {
        itemClickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }
}