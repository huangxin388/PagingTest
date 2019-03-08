package com.example.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.bean.RawBean;


public class MyPagedListAdapter extends PagedListAdapter<RawBean,MyPagedListAdapter.MyViewHolder> {

    public MyPagedListAdapter(@NonNull DiffUtil.ItemCallback<RawBean> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) parent.getContext()
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label",
                        "magnet:?xt=urn:sha1:" + getItem(position).getId());
                cm.setPrimaryClip(mClipData);
                Toast.makeText(parent.getContext(),"复制成功",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder baseViewHolder, int position) {
        RawBean bean = getItem(position);
        baseViewHolder.tvResourceName.setText(bean.getHighLightName());
        baseViewHolder.tvResourceMagnet.setText("magnet:?xt=urn:sha1:" + bean.getId());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvResourceName;
        TextView tvResourceMagnet;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResourceName = itemView.findViewById(R.id.resource_name);
            tvResourceMagnet = itemView.findViewById(R.id.resource_magnet);
        }
    }

}
