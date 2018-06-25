package com.easyapp.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.easyapp.ble.search.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

public class BleAdapter extends BaseAdapter {

    private LinkedHashMap<String, SearchResult> resultHashMap;
    private Context context;

    public BleAdapter(Context context) {
        super();
        this.context = context;
        resultHashMap = new LinkedHashMap<>();
    }

    public void add(SearchResult searchResult) {
        resultHashMap.put(searchResult.getAddress(), searchResult);
        notifyDataSetChanged();
    }

    public void clear() {
        resultHashMap.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return resultHashMap.size();
    }

    @Override
    public SearchResult getItem(int position) {
        return new ArrayList<>(resultHashMap.values()).get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.list_ble_item, null);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvAddress = convertView.findViewById(R.id.tvAddress);
            viewHolder.tvRssi = convertView.findViewById(R.id.tvRssi);
            viewHolder.tvBondState = convertView.findViewById(R.id.tvBondState);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchResult searchResult = getItem(position);
        if (searchResult != null) {
            viewHolder.tvName.setText(searchResult.getName());
            viewHolder.tvAddress.setText(searchResult.getAddress());
            viewHolder.tvRssi.setText(String.format(Locale.US, "%d\nrssi", searchResult.rssi));
            int bond = searchResult.device.getBondState();
            String bondValue = "NULL";
            if (bond == BluetoothDevice.BOND_BONDED) {
                bondValue = "BONDED";
            } else if (bond == BluetoothDevice.BOND_NONE) {
                bondValue = "Not BONDED";
            } else if (bond == BluetoothDevice.BOND_BONDING) {
                bondValue = "BONDING";
            }
            viewHolder.tvBondState.setText(bondValue);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvAddress;
        TextView tvRssi;
        TextView tvBondState;
    }

}