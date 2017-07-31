package com.example.view.myapplication.mvp.model;

import okhttp3.Callback;

/**
 * Created by view on 17-7-31.
 */

public interface ImModel {
    abstract void getData(Callback callback);
}
