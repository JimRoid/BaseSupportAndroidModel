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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by easyapp_jim on 2016/5/3.
 */
public class FragmentNews extends BaseRecyclerViewFragment {

    int count = 0;

    @Override
    protected void setOnRecycleAdapter() {

    }

    @Override
    protected void init() {
        setRecyclerViewAnimDisable();
//        setAutoHideToolBar(false);
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
        adapterItemHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(new SampleFragment());
            }
        });
    }

    @Override
    protected void getBindHeaderViewHolder(RecyclerView.ViewHolder holder, Object obj) {
        getBindViewHolder(holder, obj);
    }

    public class AdapterItemHolder extends BaseRecyclerViewAdapter.ItemHolder {
        public TextView textView;
        public View content;

        public AdapterItemHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
