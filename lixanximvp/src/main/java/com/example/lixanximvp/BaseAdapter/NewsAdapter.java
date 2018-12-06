package com.example.lixanximvp.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lixanximvp.Bean.NewsBean;
import com.example.lixanximvp.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.DataBean> list;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<NewsBean.DataBean> lists){
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }

    public void addData(List<NewsBean.DataBean> lists){
        list.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewsBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position).getThumbnail_pic_s02() == null){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(getItemViewType(position) == 0){
            ViewHolder holder;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_types,parent,false);
                holder = new ViewHolder();
                holder.text = convertView.findViewById(R.id.text1);
                holder.img = convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.text.setText(getItem(position).getTitle());
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s(),holder.img);
        }else{
            ItemViewHolder holder;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_type,parent,false);
                holder = new ItemViewHolder();
                holder.text = convertView.findViewById(R.id.textView);
                holder.img=convertView.findViewById(R.id.imageView2);
                holder.img2=convertView.findViewById(R.id.imageView3);
                holder.img3=convertView.findViewById(R.id.imageView4);
                convertView.setTag(holder);
            }else{
                holder = (ItemViewHolder) convertView.getTag();
            }
            holder.text.setText(getItem(position).getTitle());
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s(),holder.img);
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s02(),holder.img2);
            ImageLoader.getInstance().displayImage(getItem(position).getThumbnail_pic_s03(),holder.img3);
        }
        return convertView;
    }

    class ViewHolder{
        TextView text;
        ImageView img;
    }

    class ItemViewHolder{
        TextView text;
        ImageView img,img2,img3;

    }
}
