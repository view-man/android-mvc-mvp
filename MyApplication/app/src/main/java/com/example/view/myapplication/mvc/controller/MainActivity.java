package com.example.view.myapplication.mvc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.view.myapplication.R;
import com.example.view.myapplication.bean.MainBean;
import com.example.view.myapplication.mvc.model.MainModel;
import com.example.view.myapplication.mvc.view.ImUI;
import com.example.view.myapplication.mvc.view.MainUI;
import com.example.view.myapplication.mvp.view.MvpActivity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Callback, View.OnClickListener {

    private ImUI imUI;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View cintent = getWindow().getDecorView().findViewById(android.R.id.content);
        imUI = new MainUI(this, cintent);

        mainModel = new MainModel();
        mainModel.getData(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imUI.errorUI();
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            String string = response.body().string();
            final MainBean mainBean = gson.fromJson(string.substring(5, string.length() - 1), MainBean.class);
            String tag = (String) response.request().tag();
            mainBean.setTag(tag);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imUI.updataUI(mainBean);
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_button:
                startActivity(new Intent(this, MvpActivity.class));
                break;
        }
    }
}
