package com.example.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.bean.RawBean;
import com.example.bean.ResultBean;
import com.example.constant.Constant;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyViewModel extends AndroidViewModel {
    //每页需要加载的数量
    private static final int NUM_PER_PAGE = 15;
    //第一页
    private static final int PAGE_FIRST = 1;
    //当前页码数
    private int mPage = PAGE_FIRST;
    //列表数据
    private LiveData<PagedList<RawBean>> mLiveData;
    //用来存储网络返回的信息类
    private ResultBean resultBean;
    //默认的构造方法
    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PagedList<RawBean>> getLiveData(String searchContent) {
        initPagedList(searchContent);//初始化PagedList
        return mLiveData;
    }

    /**
     * 初始化PageList
     */
    private void initPagedList(String searchContent) {
        final PositionalDataSource<RawBean> positionalDataSource = new PositionalDataSource<RawBean>() {
            List<RawBean> list = new ArrayList<>();

            /**
             * recyclerView第一次加载时自动调用
             * @param params 包含当前加载的位置position、下一页加载的长度count
             * @param callback 将数据回调给UI界面使用callback.onResult
             */
            @Override
            public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<RawBean> callback) {
                //计算显示到第几条数据
                final int position = computeInitialLoadPosition(params, NUM_PER_PAGE);
                if(!TextUtils.isEmpty(searchContent)) {
                    //recyclerView第一次加载时我们调用OkHttp进行数据的加载
                    //add后面都是post请求所需要的字段及值，根据请求数据的不同有所改变
                    RequestBody body = new FormBody.Builder()
                            .add("searchValue",searchContent)
                            .add("rows","10")
                            .add("page",String.valueOf(mPage))//1
                            .build();
                    Request request = new Request.Builder()
                            .url(Constant.mainUrl + Constant.srearchUrl)
                            .post(body)
                            .build();
                    try {
                        Response response = HttpUtils.getClient().newCall(request).execute();
                        String strResponse = response.body().string();
                        //使用Gson将返回的json数据解析为javabean
                        Gson gson = new Gson();
                        //从javabean中取出我们需要的bean
                        resultBean = gson.fromJson(strResponse,new TypeToken<ResultBean>(){}.getType());
                        list.addAll(resultBean.getRows());
                        //最重要的一步，paging是基于观察者模式，我们在这里调用callback.onResult();
                        //会直接将数据list返回到UI层，等下面接受到这个list数据的数据的时候我会提醒大家
                        //如果设置占位符需要调用三个参数的onResult()方法，最后一个参数为每页的总数据量
                        //我们没有设置占位符，因此调用两个参数的方法
                        callback.onResult(list,position);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            /**
             * 当用户滑动recyclerView到下一屏的时候自动调用，这里我们自动加载下一页的数据
             * @param params 包含当前加载的位置position、下一页加载的长度count
             * @param callback 将数据回调给UI界面使用callback.onResult
             */
            @Override
            public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<RawBean> callback) {
                if(!TextUtils.isEmpty(searchContent)) {
                    //每次加载到分页条目时页数变为下一页
                    mPage++;
                    //使用OkHttp加载下一页数据
                    RequestBody body = new FormBody.Builder()
                            .add("searchValue",searchContent)
                            .add("rows","10")
                            .add("page",String.valueOf(mPage))
                            .build();
                    Request request = new Request.Builder()
                            .url(Constant.mainUrl + Constant.srearchUrl)
                            .post(body)
                            .build();
                    try {
                        Response response = HttpUtils.getClient().newCall(request).execute();
                        String strResponse = response.body().string();
                        //使用Gson解析数据
                        Gson gson = new Gson();
                        resultBean = gson.fromJson(strResponse,new TypeToken<ResultBean>(){}.getType());
                        list.addAll(resultBean.getRows());
                        callback.onResult(list);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        // 构建LiveData
        mLiveData = new LivePagedListBuilder(new PageDataSourceFactory(positionalDataSource)//自己定义
                , new PagedList.Config.Builder().setPageSize(NUM_PER_PAGE)//每次加载的数据数量15
                //距离本页数据几个时候开始加载下一页数据(例如现在加载10个数据,设置prefetchDistance为2,则滑到第八个数据时候开始加载下一页数据).
                .setPrefetchDistance(NUM_PER_PAGE)//15
                //这里设置是否设置PagedList中的占位符,如果设置为true,我们的数据数量必须固定,由于网络数据数量不固定,所以设置false.
                .setEnablePlaceholders(false).setInitialLoadSizeHint(NUM_PER_PAGE)//15
                .build()).build();

    }
}
