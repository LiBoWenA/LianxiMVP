package com.example.mvp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mvp.BaseAdapter.MyBase;
import com.example.mvp.Persenter.IPersenterImpl;
import com.example.mvp.View.IView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{

    IPersenterImpl iPersenter;
    private MyBase myBase;
    private String path = "http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=1&custom_fields=thumb_c,views&dev=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPersenter = new IPersenterImpl(this);
        init();
    }

    private void init() {

        ListView listView = findViewById(R.id.listview);
        //获取资源ID
        myBase = new MyBase(this);
        listView.setAdapter(myBase);
        checkPermission();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPersenter != null) {
            iPersenter.onDetach();
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 100);
            } else {
                startRequest();
            }
        } else {
            startRequest();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果requestCode匹配，切权限申请通过
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startRequest();
        }
    }

    public void startRequest(){
        iPersenter.startRequestData(path,null,Bean.class);
    }

    //请求成功返回的数据
    @Override
    public void showRequestData(Object data) {
        Bean bean = (Bean) data;
        myBase.setData(bean.getPosts());
    }
}
