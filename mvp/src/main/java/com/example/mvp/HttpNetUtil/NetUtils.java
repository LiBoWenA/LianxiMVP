package com.example.mvp.HttpNetUtil;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtils {
    //单例模式
    private NetUtils instance;
    private Gson gson;

    public NetUtils getInstance() {
        if (instance == null){
            instance = new NetUtils();
        }
        return instance;
    }
    //gson解析
/*
    public static void getJson(String path, final Class clazz, final CallBack callBack){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return getJson(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callBack.sucesses(o);
            }
        }.execute(path);
    }

    public interface CallBack<T>{
        void sucesses(T o);
    }

    public static <T> T getJson(String path,Class clazz){
        String json = getJson(path);
        T o = (T) new Gson().fromJson(json, clazz);
        return o;
    }*/

    public static String getJson(String path){
        String json = "";
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200){
                json = stream2string(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    //流转换
    public static String stream2string(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader b = new BufferedReader(new InputStreamReader(is));
        for(String t = b.readLine(); t!= null; t = b.readLine()){
            builder.append(t);
        }
        return builder.toString();
    }
}
