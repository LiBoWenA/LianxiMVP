package com.example.mvp.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvp.Bean;
import com.example.mvp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {
    private Context context;
    private List<Bean.PostsBean> list;

    public MyBase(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<Bean.PostsBean> lists){
        list = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Bean.PostsBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.imageView);
            holder.text = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(getItem(position).getTitle());
        ImageLoader.getInstance().displayImage(getItem(position).getCustom_fields().getThumb_c().get(0),holder.img);
        return convertView;
    }

    class ViewHolder{
        TextView text;
        ImageView img;
    }
}
