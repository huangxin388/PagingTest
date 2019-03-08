package com.example.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.myapplication.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTianti;
    private Button btnFirst;
    private Button btnDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        btnTianti = findViewById(R.id.btn_tianti);
        btnFirst = findViewById(R.id.btn_top_refresh);
        btnDatabase = findViewById(R.id.btn_database);
        btnTianti.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_tianti:
            intent = new Intent(TestActivity.this,MainActivity.class);
            break;
            case R.id.btn_top_refresh:
                intent = new Intent(TestActivity.this,FirstActivity.class);
                break;
            case R.id.btn_database:
                intent = new Intent(TestActivity.this,MysqlActivity.class);
                break;
                default:
        }
        startActivity(intent);

    }
}
