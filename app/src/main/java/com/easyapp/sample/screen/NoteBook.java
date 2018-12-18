package com.easyapp.sample.screen;


import android.view.View;
import android.widget.TextView;

import com.easyapp.database.EasyDB;
import com.easyapp.lib.menu.MenuView;
import com.easyapp.lib.recyclerView.BaseList;
import com.easyapp.lib.recyclerView.BaseRecyclerViewAdapter;
import com.easyapp.sample.R;
import com.easyapp.sample.http.entity.EntityDiscuss;
import com.easyapp.sample.storage.Note;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 筆記本
 */
public class NoteBook extends BaseList<NoteBook.ViewHolder, Note> {

    public static NoteBook instance() {
        return new NoteBook();
    }

    @Override
    protected void init() {
        showFab();
        getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment(NoteCreate.instance());
            }
        });
    }

    @Override
    protected void onLoad() {
        ArrayList<Note> notes = new ArrayList<>();

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
    public ViewHolder onCreateViewHolderContent(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int onViewHolderLayoutContent() {
        return R.layout.item_note_book;
    }

    static class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
