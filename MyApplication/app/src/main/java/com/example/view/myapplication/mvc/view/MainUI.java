package com.example.view.myapplication.mvc.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.view.myapplication.MainAdapter;
import com.example.view.myapplication.R;
import com.example.view.myapplication.UrlUtils;
import com.example.view.myapplication.bean.ImBean;
import com.example.view.myapplication.bean.MainBean;
import com.example.view.myapplication.mvc.controller.MainActivity;

/**
 * Created by view on 17-7-31.
 */

public class MainUI implements ImUI {

    private ListView listView;
    private Context context;
    private View.OnClickListener onClickListener;

    public MainUI(MainActivity activity, View view) {
        this.context = activity;
        this.onClickListener = activity;
        findView(view);
    }

    private void findView(View view) {
        listView = view.findViewById(R.id.lv_list);
        Button button = view.findViewById(R.id.bt_button);
        button.setOnClickListener(onClickListener);
    }


    @Override
    public void updataUI(ImBean imBean) {
        switch (imBean.getTag()) {
            case UrlUtils.TEST_DATA:
                MainAdapter mainAdapter = new MainAdapter(context, ((MainBean) imBean).getResult());
                listView.setAdapter(mainAdapter);
                break;
        }
    }

    @Override
    public void errorUI() {
        Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
    }
}
