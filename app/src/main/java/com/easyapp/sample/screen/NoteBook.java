package com.easyapp.sample.screen;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easyapp.lib.menu.MenuView;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;
import com.easyapp.sample.R;
import com.easyapp.sample.base.BaseAppList;
import com.easyapp.sample.storage.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筆記本
 */
public class NoteBook extends BaseAppList<NoteBook.ViewHolder, Note> {

    Handler handler;

    public static NoteBook instance() {
        return new NoteBook();
    }

    @Override
    public void onViewCreated() {
        handler = new Handler();
        final MenuView menuView = new MenuView(getContext());
        menuView.setMenuText("新增");
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(NoteCreate.instance());
            }
        });
        getRightMenu().addView(menuView.getMenu());
    }

    @Override
    protected void onLoad() {
        Note note = new Note();
        note.getStore();

        ArrayList<Note> notes = new ArrayList<>();
        showLoading();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancelLoading();
            }
        }, 1000 * 10);
    }

    @Override
    public void onBindViewHolderContent(ViewHolder viewHolder, Note note) {
        viewHolder.tvTitle.setText(note.getTitle());
        viewHolder.tvContent.setText(note.getContent());
    }

    @Override
    protected int onGridLayoutSpanCount() {
        return 1;
    }

    @Override
    public ViewHolder onCreateViewHolderContent(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        View view = mInflater.inflate(R.layout.item_note_book, viewGroup, false);
        return new ViewHolder(view, viewType);
    }

    static class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;

        public ViewHolder(View itemView, int type) {
            super(itemView, type);
            ButterKnife.bind(this, itemView);
        }
    }
}
