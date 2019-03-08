package com.example.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adapter.MyPagedListAdapter;
import com.example.admin.myapplication.R;
import com.example.bean.RawBean;
import com.example.view.MyViewModel;


public class VPNFragment extends Fragment {

    private Context mContext;

    private EditText etSearchContent;
    private ImageView ivSearchIcon;
    private RecyclerView lvSearchResult;

    private MyPagedListAdapter adapter;
    private MyViewModel myViewModel;
    private LiveData<PagedList<RawBean>> mLiveData;
    private Observer<PagedList<RawBean>> observer;

    private String strSearchContent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vpn,container,false);
        initData();
        initView(view);
        initEvent();
        return view;
    }

    /**、
     * 初始化数据
     */
    private void initData() {
        mContext = getActivity();
        myViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MyViewModel.class);
        mLiveData = myViewModel.getLiveData("");
    }

    /**
     * 初始化View
     * @param view
     */
    private void initView(View view) {
        etSearchContent = view.findViewById(R.id.et_search_content);
        ivSearchIcon = view.findViewById(R.id.iv_search_icon);
        lvSearchResult = view.findViewById(R.id.rv_search_result);
        initAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        lvSearchResult.setAdapter(adapter);
        lvSearchResult.setLayoutManager(manager);

        observer = new Observer<PagedList<RawBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<RawBean> rawBeans) {
                adapter.submitList(rawBeans);
            }
        };

        mLiveData.observe(getActivity(),observer);
    }

    private void initAdapter() {
        adapter = new MyPagedListAdapter(new DiffUtil.ItemCallback<RawBean>(){

            @Override
            public boolean areItemsTheSame(@NonNull RawBean rawBean, @NonNull RawBean t1) {
                return rawBean.getId().equals(t1.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull RawBean rawBean, @NonNull RawBean t1) {
                return rawBean.equals(t1);
            }
        });
    }

    /**
     * 初始化事件
     */
    private void initEvent() {

        ivSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSearchContent = etSearchContent.getText().toString().trim();
                if(!TextUtils.isEmpty(strSearchContent)) {
                    //showToast("我是不会为你搜索的");
                    mLiveData = myViewModel.getLiveData(strSearchContent);
                    if(mLiveData != null) {
                        mLiveData.removeObserver(observer);
                    }
                    mLiveData.observe(getActivity(),observer);
                } else {
                    showToast("请输入要搜索的内容");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("xxx","VPNFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("xxx","VPNFragment onPause");
        mLiveData.removeObserver(observer);

    }

    private void showToast(String str) {
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
    }
}
