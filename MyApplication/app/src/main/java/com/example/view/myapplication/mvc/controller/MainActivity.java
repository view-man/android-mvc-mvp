package com.example.view.myapplication.mvc.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.view.myapplication.R;
import com.example.view.myapplication.mvc.model.MainModel;
import com.example.view.myapplication.mvc.view.ImUI;
import com.example.view.myapplication.mvc.view.MainUI;
import com.example.view.myapplication.mvp.view.MvpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImUI imUI;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View cintent = getWindow().getDecorView().findViewById(android.R.id.content);
        imUI = new MainUI(this, cintent);
        mainModel = new MainModel(imUI);
        mainModel.getData();
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
