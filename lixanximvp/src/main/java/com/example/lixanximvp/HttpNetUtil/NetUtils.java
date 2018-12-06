package com.example.lixanximvp.HttpNetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtils {

    //判断账号密码
    public static boolean isOk(){
        return true;
    }

    //解析
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
        for(String t = b.readLine();t != null;t = b.readLine()){
            builder.append(t);
        }
        return builder.toString();
    }
}
