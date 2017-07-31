package com.example.view.myapplication.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.view.myapplication.MainAdapter;
import com.example.view.myapplication.R;
import com.example.view.myapplication.UrlUtils;
import com.example.view.myapplication.bean.ImBean;
import com.example.view.myapplication.bean.MainBean;
import com.example.view.myapplication.mvp.presenter.MvpPresenter;
import com.example.view.myapplication.mvp.view.ImUI;

/**
 * Created by view on 17-7-31.
 */

public class MvpActivity extends AppCompatActivity implements ImUI{

    private MvpPresenter mvpPresenter;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        listView = (ListView) findViewById(R.id.lv_list);
        mvpPresenter = new MvpPresenter(this);
        mvpPresenter.getData();
    }

    @Override
    public void updata(ImBean imBean) {
        switch (imBean.getTag()){
            case UrlUtils.TEST_DATA:
                MainAdapter mainAdapter = new MainAdapter(this, ((MainBean) imBean).getResult());
                listView.setAdapter(mainAdapter);
                break;
        }
    }

    @Override
    public void errorUI() {
        Toast.makeText(this,"网络异常",Toast.LENGTH_SHORT).show();
    }
}
