package com.example.mvp.Moudle;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.mvp.Bean;
import com.example.mvp.CallBack.MyCallBack;
import com.example.mvp.HttpNetUtil.NetUtils;
import com.google.gson.Gson;

public class IMoudleImpl implements IMoudle {
    private MyCallBack myCallBack;
    @SuppressLint("StaticFieldLeak")
    @Override
    public void requestData(String url, String params, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        new AsyncTask<String, Void, Bean>() {
            @Override
            protected Bean doInBackground(String... strings) {
                String json = NetUtils.getJson(strings[0]);
                Bean bean = new Gson().fromJson(json, Bean.class);
                return bean;
            }

            @Override
            protected void onPostExecute(Bean bean) {
                if (myCallBack != null){
                    myCallBack.secesses(bean);
                }
            }
        }.execute(url);
    }
}
