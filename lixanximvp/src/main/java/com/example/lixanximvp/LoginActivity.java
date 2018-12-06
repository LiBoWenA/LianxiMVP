package com.example.lixanximvp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.lixanximvp.Fragment.Fragment_list;
import com.example.lixanximvp.Fragment.Fragment_name;
import com.example.lixanximvp.Persenter.IPersenterImpl;
import com.example.lixanximvp.View.IView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity{

    private ViewPager pager;
    private RadioGroup group;
    private List<Fragment> list;
    private String name;
    IPersenterImpl iPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        //获取资源ID
        pager = findViewById(R.id.pager);
        group = findViewById(R.id.group);
        list = new ArrayList<>();
        list.add(new Fragment_list());
        list.add(new Fragment_name());

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }
        });

    }

    public String getName(){
        return name;
    }


}
