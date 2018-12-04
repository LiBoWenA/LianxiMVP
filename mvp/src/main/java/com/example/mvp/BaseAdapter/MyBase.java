package com.example.mvp.BaseAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvp.Bean;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {
    private Context context;
    private List<Bean> list;

    public MyBase(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<Bean> lists){
        list = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Bean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    class ViewHolder{
        TextView text;
        ImageView img;
    }
}
