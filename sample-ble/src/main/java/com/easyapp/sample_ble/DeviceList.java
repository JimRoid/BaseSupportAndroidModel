package com.easyapp.sample_ble;

import android.view.View;
import android.widget.TextView;

import com.easyapp.ble.search.SearchRequest;
import com.easyapp.ble.search.SearchResult;
import com.easyapp.ble.search.response.SearchResponse;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceList extends BaseAppList<DeviceList.ViewHolder, SearchResult> {

    private ArrayList<SearchResult> searchResults;

    @Override
    protected void init() {
        searchResults = new ArrayList<>();
    }

    @Override
    protected void onLoad() {
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(5000)
                .build();
        BleManager.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {
                searchResults.clear();
                Logger.d("onSearchStarted");
            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                searchResults.add(device);
                Logger.d("onDeviceFounded");
                setData(searchResults);
            }

            @Override
            public void onSearchStopped() {
                Logger.d("onSearchStopped");
            }

            @Override
            public void onSearchCanceled() {
                Logger.d("onSearchCanceled");
            }
        });
    }

    @Override
    public void onBindViewHolderContent(ViewHolder viewHolder, SearchResult searchResult) {
        viewHolder.tvDeviceName.setText(searchResult.device.getName());
    }

    @Override
    public DeviceList.ViewHolder onCreateViewHolderContent(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int onViewHolderLayoutContent() {
        return R.layout.recycler_ble_item;
    }

    static class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        @BindView(R.id.tvRssi)
        TextView tvRssi;
        @BindView(R.id.tvDeviceName)
        TextView tvDeviceName;
        @BindView(R.id.tvDeviceMacAddress)
        TextView tvDeviceMacAddress;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
