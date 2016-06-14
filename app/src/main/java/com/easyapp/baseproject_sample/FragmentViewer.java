package com.easyapp.baseproject_sample;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.easyapp.baseproject.lib.baseFragment.list.BaseRecyclerViewFragment;
import com.easyapp.baseproject.lib.recycleView.BaseRecyclerViewAdapter;
import com.easyapp.baseproject.lib.tool.CollectionTool;
import com.easyapp.baseproject.lib.tool.RawTool;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class FragmentViewer extends BaseRecyclerViewFragment {

    int count = 0;

    @Override
    protected void setOnRecycleAdapter() {

    }


    @Override
    protected void init() {
//        setFabSrc(1);
//        setFabVisible(false);
//        setFabBackground();
        Gson gson = new Gson();
        String json = RawTool.getRawString(getActivity(), R.raw.proj);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            ItemNews[] itemNewses = gson.fromJson(jsonArray.toString(), ItemNews[].class);
            List<ItemNews> newses = CollectionTool.arrayToList(itemNewses);
            setData(newses);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int setGridLayoutSpanCount() {
        return 2;
    }

    @Override
    protected void onLoadMore() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                String json = RawTool.getRawString(getActivity(), R.raw.proj);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");
                    ItemNews[] itemNewses = gson.fromJson(jsonArray.toString(), ItemNews[].class);
                    List<ItemNews> newses = CollectionTool.arrayToList(itemNewses);
                    count++;
                    addData(newses);
                } catch (JSONException e) {

                }
            }
        }, 200);
    }

    @Override
    public void onRefresh() {
        onLoadMore();
    }

    @Override
    protected int getRecycleViewHolderLayout() {
        return R.layout.item_image;
    }

    @Override
    protected int getRecycleViewHolderHeaderLayout() {
        return getRecycleViewHolderLayout();
    }

    @Override
    protected BaseRecyclerViewAdapter.ItemHolder getItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }

    @Override
    protected BaseRecyclerViewAdapter.ItemHolder getHeaderItemHolder(View contactView) {
        return new AdapterItemHolder(contactView);
    }

    @Override
    protected void getBindViewHolder(RecyclerView.ViewHolder holder, Object obj) {
        AdapterItemHolder adapterItemHolder = (AdapterItemHolder) holder;

        Glide.with(getContext()).load("https://dl.dropboxusercontent.com/u/173576968/big.jpg").into(adapterItemHolder.iv_picture);
    }

    @Override
    protected void getBindHeaderViewHolder(RecyclerView.ViewHolder holder, Object obj) {
        getBindViewHolder(holder, obj);
    }

    public class AdapterItemHolder extends BaseRecyclerViewAdapter.ItemHolder {
        public ImageView iv_picture;


        public AdapterItemHolder(View itemView) {
            super(itemView);
            iv_picture = (ImageView) itemView.findViewById(R.id.iv_picture);
        }
    }
}
