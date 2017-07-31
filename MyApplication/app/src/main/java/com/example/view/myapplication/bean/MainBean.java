package com.example.view.myapplication.bean;

import java.util.List;

/**
 * Created by view on 17-7-31.
 */

public class MainBean implements ImBean {

    private String tag;
    private List<List<String>> result;

    @Override
    public void setTag(String tag) {
        this.tag=tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public List<List<String>> getResult() {
        return result;
    }

    public void setResult(List<List<String>> result) {
        this.result = result;
    }

}
