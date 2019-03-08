package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import com.example.admin.myapplication.R;

public class TopRefreshListView extends ListView {
    View headerView;
    int headViewHeight;
    public TopRefreshListView(Context context) {
        this(context,null);
    }

    public TopRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TopRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        headerView = LayoutInflater.from(context).inflate(R.layout.header_layout,null);
        measureView(headerView);
        headViewHeight = headerView.getMeasuredHeight();
        Log.d("xxx","headViewHeight = " + headViewHeight);
        topPadding(-headViewHeight);
        this.addHeaderView(headerView);
    }
    private void topPadding(int topPadding) {
        headerView.setPadding(headerView.getPaddingLeft(),topPadding,headerView.getPaddingRight(),headerView.getPaddingBottom());
        headerView.invalidate();
    }

    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if(p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight = p.height;
        if(tempHeight > 0) {
            height = MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        view.measure(width,height);
    }
}
