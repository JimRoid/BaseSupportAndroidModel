package com.easyapp.image.adapter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.easyapp.image.R;
import com.easyapp.image.bean.Folder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;


/**
 * 文件夾adapter
 */
public class FolderAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private List<Folder> mFolders = new ArrayList<>();

    private int lastSelected = 0;

    private RequestOptions glideOptions;

    public FolderAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        glideOptions = new RequestOptions()
                .placeholder(R.drawable.default_error)
                .centerCrop();
    }

    /**
     * @param folders
     */
    public void setData(List<Folder> folders) {
        if (folders != null && folders.size() > 0) {
            mFolders = folders;
        } else {
            mFolders.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFolders.size() + 1;
    }

    @Override
    public Folder getItem(int i) {
        if (i == 0) return null;
        return mFolders.get(i - 1);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.list_item_folder, viewGroup, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (holder != null) {
            if (i == 0) {
                holder.name.setText(R.string.folder_all);
                holder.path.setText(Environment.getExternalStorageDirectory().getPath());
                holder.size.setText(String.format(Locale.ENGLISH, "%d%s", getTotalImageSize(), mContext.getResources().getString(R.string.photo_unit)));
                if (mFolders.size() > 0) {
                    Folder f = mFolders.get(0);
                    Glide.with(mContext)
                            .load(new File(f.cover.path))
                            .apply(glideOptions)
                            .into(holder.cover);
                }
            } else {
                if (getItem(i) != null) {
                    Folder data = getItem(i);
                    holder.name.setText(data.name);
                    holder.path.setText(data.path);
                    if (data.images != null) {
                        holder.size.setText(String.format(Locale.ENGLISH, "%d%s", data.images.size(), mContext.getResources().getString(R.string.photo_unit)));
                    } else {
                        holder.size.setText("*" + mContext.getResources().getString(R.string.photo_unit));
                    }
                    // 顯示圖片
                    if (data.cover != null) {
                        Glide.with(mContext)
                                .load(data.cover.path)
                                .apply(glideOptions)
                                .into(holder.cover);
                    } else {
                        holder.cover.setImageResource(R.drawable.default_error);
                    }
                }
            }
            if (lastSelected == i) {
                holder.indicator.setVisibility(View.VISIBLE);
            } else {
                holder.indicator.setVisibility(View.INVISIBLE);
            }
        }
        return view;
    }

    private int getTotalImageSize() {
        int result = 0;
        if (mFolders != null && mFolders.size() > 0) {
            for (Folder f : mFolders) {
                result += f.images.size();
            }
        }
        return result;
    }

    public void setSelectIndex(int i) {
        if (lastSelected == i) return;

        lastSelected = i;
        notifyDataSetChanged();
    }

    public int getSelectIndex() {
        return lastSelected;
    }

    class ViewHolder {
        ImageView cover;
        TextView name;
        TextView path;
        TextView size;
        ImageView indicator;

        ViewHolder(View view) {
            cover = view.findViewById(R.id.cover);
            name = view.findViewById(R.id.name);
            path = view.findViewById(R.id.path);
            size = view.findViewById(R.id.size);
            indicator = view.findViewById(R.id.indicator);
            view.setTag(this);
        }
    }

}
