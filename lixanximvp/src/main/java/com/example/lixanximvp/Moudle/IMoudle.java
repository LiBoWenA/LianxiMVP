package com.example.lixanximvp.Moudle;

import com.example.lixanximvp.CallBack.MyCallBack;

public interface IMoudle {
    void RequestData(String path, String params, Class Clazz, MyCallBack myCallBack);
}
