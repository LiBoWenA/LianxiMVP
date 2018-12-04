package com.example.mvp.Persenter;

import com.example.mvp.CallBack.MyCallBack;
import com.example.mvp.Moudle.IMoudle;
import com.example.mvp.Moudle.IMoudleImpl;
import com.example.mvp.View.IView;

public class IPersenterImpl implements IPersenter {
    //实例化IView和IMoudel
    private IMoudle iMoudle;
    private IView iView;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iMoudle = new IMoudleImpl();
    }

    @Override
    public void startRequestData(String url, String params) {
        //调用moudle里面的解析方法，并回传给iview
        iMoudle.requestData(url, params, new MyCallBack() {
            @Override
            public void secesses(Object data) {
                iView.showRequestData(data);
            }
        });
    }

    public void onDetach(){
        //解除绑定
        if (iMoudle != null){
            iMoudle = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
