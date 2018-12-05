package com.example.mvp.Moudle;

import com.example.mvp.CallBack.MyCallBack;

public interface IMoudle {
    void requestData(String url, String params, Class clazz,MyCallBack myCallBack);
}
