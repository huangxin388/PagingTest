package com.example.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adapter.MyPagerAdapter;
import com.example.adapter.ResourceAdapter;
import com.example.admin.myapplication.R;
import com.example.bean.ResultBean;
import com.example.fragment.DigFragment;
import com.example.fragment.PersonFragment;
import com.example.fragment.VPNFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity";

    private ViewPager vp_home;
    private RadioButton rb_vpn;
    private RadioButton rb_dug;
    private RadioButton rb_person;
    private RadioGroup rg_home;
    private View mSpaceLineView;
    private ArrayList<RadioButton> radioButtons;
    private MyPagerAdapter adapter;
    private List<Fragment> fragments;

    private long mBackTime = 0L;//记录点击返回键的时间点

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(setContentViewId());
//    }

    @Override
    public int setContentViewId() {
        //getWindow().setBackgroundDrawable(null);//   防止过度绘制
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Log.d("xxx","initView被调用");
        vp_home = findViewById(R.id.vp_home_activity);//viewpager
        rb_vpn = findViewById(R.id.rb_vpn);//vpn按钮
        rb_dug = findViewById(R.id.rb_dug);//挖币按钮
        rb_person = findViewById(R.id.rb_person);//个人信息按钮
        rg_home = findViewById(R.id.rg_home);//RadioGroup
        radioButtons = new ArrayList<>();
        mSpaceLineView = findViewById(R.id.home_space_line);//分割线
    }

    @Override
    public void initData() {
        Log.d("xxx","initData被调用");
        radioButtons.add(0, rb_vpn);
        radioButtons.add(1, rb_dug);
        radioButtons.add(2, rb_person);
        rg_home.check(R.id.rb_vpn);

        fragments = new ArrayList<>();
        fragments.add(new VPNFragment());
        fragments.add(new DigFragment());
        fragments.add(new PersonFragment());

        vp_home.setOffscreenPageLimit(3);
        adapter = new MyPagerAdapter(getSupportFragmentManager(),MainActivity.this,fragments);
        vp_home.setAdapter(adapter);
        vp_home.addOnPageChangeListener(this);

        initListenter();



    }

    private void initListenter() {
        rg_home.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_vpn:
                        vp_home.setCurrentItem(0);
                        break;
                    case R.id.rb_dug:
                        vp_home.setCurrentItem(1);
                        break;
                    case R.id.rb_person:
                        vp_home.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0;i < radioButtons.size();i++) {
            if(position == i) {
                fragments.get(i).onResume();
            } else {
                fragments.get(i).onPause();
            }
        }
        if(radioButtons != null) {
            radioButtons.get(position).setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - mBackTime < 2000) {
            super.onBackPressed();
        } else {
            mBackTime = System.currentTimeMillis();
            Toast.makeText(MainActivity.this,getString(R.string.home_twice_back_exit),Toast.LENGTH_SHORT).show();
        }

    }
}
