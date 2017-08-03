package com.example.view.myapplication.mvc.model;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.view.myapplication.UrlUtils;
import com.example.view.myapplication.bean.MainBean;
import com.example.view.myapplication.mvc.view.ImUI;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by view on 17-7-31.
 */

public class MainModel implements IMmodel ,Callback{

    private ImUI imUI;

    public MainModel(ImUI imUI){
        this.imUI= imUI;
    }

    @Override
    public void getData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(UrlUtils.TEST_DATA).tag(UrlUtils.TEST_DATA).build();
        okHttpClient.newCall(request).enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
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
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    imUI.updataUI(mainBean);
                }
            });
        }
    }
}
