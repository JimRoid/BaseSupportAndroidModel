package com.easyapp.baseproject_sample;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.easyapp.baseproject.lib.baseFragment.BaseRecyclerViewFragment;
import com.easyapp.baseproject.lib.recycleView.BaseRecyclerViewAdapter;
import com.easyapp.baseproject.lib.tool.CollectionTool;
import com.easyapp.baseproject.lib.tool.RawTool;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class FragmentNews extends BaseRecyclerViewFragment {

    int count = 0;

    @Override
    protected void init() {
        Gson gson = new Gson();
        String json = RawTool.getRawString(getActivity(), R.raw.proj);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");
            ItemNews[] itemNewses = gson.fromJson(jsonArray.toString(), ItemNews[].class);
            List<ItemNews> newses = CollectionTool.arrayToList(itemNewses);
            Logger.d("newses" + newses.size());
            setData(new ArrayList());
        } catch (JSONException e) {

        }
    }

    @Override
    protected int setGridLayoutSpanCount() {
        return 1;
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
                    Logger.d("newses" + newses.size());
                    count++;
                    addData(newses);
                } catch (JSONException e) {

                }
            }
        }, 5000);
    }

    @Override
    public void onRefresh() {
        onLoadMore();
    }

    @Override
    protected int getRecycleViewHolderLayout() {
        return R.layout.item_news_layout;
    }

    @Override
    protected int getRecycleViewHolderHeaderLayout() {
        return R.layout.item_news_layout;
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
        ItemNews itemNews = (ItemNews) obj;
        adapterItemHolder.textView.setText(itemNews.getName());
    }

    @Override
    protected void getBindHeaderViewHolder(RecyclerView.ViewHolder holder, Object obj) {
        AdapterItemHolder adapterItemHolder = (AdapterItemHolder) holder;
        ItemNews itemNews = (ItemNews) obj;
        adapterItemHolder.textView.setText(itemNews.getName());
    }

    public class AdapterItemHolder extends BaseRecyclerViewAdapter.ItemHolder {
        public TextView textView;

        public AdapterItemHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
