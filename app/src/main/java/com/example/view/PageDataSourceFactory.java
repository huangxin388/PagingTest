package com.example.view;

import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;

import com.example.bean.RawBean;

public class PageDataSourceFactory extends DataSource.Factory {
    public PositionalDataSource<RawBean> mPositionalDataSource;
    public PageDataSourceFactory(PositionalDataSource<RawBean> positionalDataSource) {
        this.mPositionalDataSource = positionalDataSource;
    }

    @Override
    public DataSource create() {
        return mPositionalDataSource;
    }
}
