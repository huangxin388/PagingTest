package com.example.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.myapplication.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());

        initView();
        initData();
    }

    public abstract @LayoutRes
    int setContentViewId();

    public abstract void initView();

    public abstract void initData();
}
