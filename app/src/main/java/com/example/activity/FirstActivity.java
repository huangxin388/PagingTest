package com.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adapter.TopRefreshAdapter;
import com.example.admin.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private ListView lvTopRefresh;
    private TopRefreshAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        lvTopRefresh = findViewById(R.id.lv_top_refresh);
        initData();
        getAdapter();
        lvTopRefresh.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        for(int i = 0;i < 30;i++) {
            data.add("name" + (i+1));
        }
    }

    private void getAdapter() {
        if(adapter == null) {
            adapter = new TopRefreshAdapter(FirstActivity.this,data);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
