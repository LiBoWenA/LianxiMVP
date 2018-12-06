package com.example.lixanximvp.Persenter;

import com.example.lixanximvp.Bean.Bean;
import com.example.lixanximvp.CallBack.MyCallBack;
import com.example.lixanximvp.Moudle.IMoudelImpl;
import com.example.lixanximvp.Moudle.IMoudle;
import com.example.lixanximvp.View.IView;

public class IPersenterImpl implements IPersenter {

    private IMoudle iMoudle;
    private IView iView;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iMoudle = new IMoudelImpl();
    }

    @Override
    public void showRequestData(String path, String params, Class Clazz) {
        iMoudle.RequestData(path, null, Clazz, new MyCallBack() {
            @Override
            public void sucesses(Object data) {
                iView.startRequestData(data);
            }
        });
    }

    public void onDestory(){
        if (iMoudle != null){
            iMoudle = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
