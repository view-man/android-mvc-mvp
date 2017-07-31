package com.example.view.myapplication.mvc.model;

import com.example.view.myapplication.UrlUtils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by view on 17-7-31.
 */

public class MainModel implements IMmodel {

    @Override
    public void getData(Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(UrlUtils.TEST_DATA).tag(UrlUtils.TEST_DATA).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
