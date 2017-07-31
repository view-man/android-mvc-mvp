package com.example.view.myapplication.mvp.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.view.myapplication.bean.MainBean;
import com.example.view.myapplication.mvp.model.MvpModel;
import com.example.view.myapplication.mvp.view.ImUI;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by view on 17-7-31.
 */

public class MvpPresenter implements ImPresenter, Callback{

    private ImUI imUI;

    public MvpPresenter(ImUI imUI){
        this.imUI = imUI;
    }

    public void getData(){
        MvpModel mvpModel = new MvpModel();
        mvpModel.getData(this);
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
        if (response.isSuccessful()){
            Gson gson = new Gson();
            String string = response.body().string();
            final MainBean mainBean = gson.fromJson(string.substring(5,string.length()-1), MainBean.class);
            String tag = (String) response.request().tag();
            mainBean.setTag(tag);

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    imUI.updata(mainBean);
                }
            });
        }
    }
}
