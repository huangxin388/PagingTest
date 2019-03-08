package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.bean.RawBean;

import java.util.List;

public class ResourceAdapter extends BaseAdapter {
    private Context mContext;
    private List<RawBean> data;

    public ResourceAdapter(Context mContext, List<RawBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.tvResourceName = convertView.findViewById(R.id.resource_name);
            holder.tvResourceMagnet = convertView.findViewById(R.id.resource_magnet);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RawBean bean = data.get(position);
        holder.tvResourceName.setText(bean.getHighLightName());
        holder.tvResourceMagnet.setText("magnet:?xt=urn:sha1:" + bean.getId());
        return convertView;
    }
    class ViewHolder {
        TextView tvResourceName;
        TextView tvResourceMagnet;
    }
}
