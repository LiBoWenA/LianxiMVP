package com.example.lixanximvp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lixanximvp.BaseAdapter.NewsAdapter;
import com.example.lixanximvp.Bean.NewsBean;
import com.example.lixanximvp.Persenter.IPersenterImpl;
import com.example.lixanximvp.R;
import com.example.lixanximvp.View.IView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class Fragment_list extends Fragment implements IView {

    private PullToRefreshListView listView;
    private NewsAdapter adapter;
    private IPersenterImpl iPersenter;
    private int page;
    private String path = "http://www.xieast.com/api/news/news.php?page=%d";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_data,null);
        //获取资源ID
        listView = view.findViewById(R.id.listview);
        page = 1;
        adapter = new NewsAdapter(getActivity());
        iPersenter = new IPersenterImpl(this);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startRequest();
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                startRequest();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                startRequest();
            }
        });
        startRequest();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (iPersenter != null) {
            iPersenter.onDestory();
        }
    }

    public void startRequest(){
        iPersenter.showRequestData(String.format(path,page),null,NewsBean.class);
    }

    @Override
    public void startRequestData(Object data) {
        NewsBean bean = (NewsBean) data;

        if (page == 1) {

            adapter.setData(bean.getData());
        }else{
            adapter.addData(bean.getData());
        }
        page++;
        listView.onRefreshComplete();

    }
}
