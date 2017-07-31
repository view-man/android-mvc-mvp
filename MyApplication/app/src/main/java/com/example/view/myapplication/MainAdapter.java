package com.example.view.myapplication;

import android.content.Context;

import java.util.List;

/**
 * Created by view on 17-7-31.
 */

public class MainAdapter extends CommonAdapter<List<String>> {


    public MainAdapter(Context context, List<List<String>> datas) {
        super(context, datas);
    }

    @Override
    public void convert(ViewHolder holder, List<String> list, int mPosition) {
        holder.setText(R.id.tv_text,list.get(0));
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_main;
    }
}
