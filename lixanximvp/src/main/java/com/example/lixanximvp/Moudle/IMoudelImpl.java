package com.example.lixanximvp.Moudle;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.lixanximvp.Bean.Bean;
import com.example.lixanximvp.CallBack.MyCallBack;
import com.example.lixanximvp.HttpNetUtil.NetUtils;
import com.google.gson.Gson;

public class IMoudelImpl implements IMoudle {

    private MyCallBack myCallBack;
    @SuppressLint("StaticFieldLeak")
    @Override
    public void RequestData(String path, String params, final Class Clazz, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                String json = NetUtils.getJson(strings[0]);

                return new Gson().fromJson(json,Clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                myCallBack.sucesses(o);
            }
        }.execute(path);
    }
}
