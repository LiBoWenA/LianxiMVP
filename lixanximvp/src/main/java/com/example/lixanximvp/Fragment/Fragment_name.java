package com.example.lixanximvp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lixanximvp.LoginActivity;
import com.example.lixanximvp.MainActivity;
import com.example.lixanximvp.R;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class Fragment_name extends Fragment {

    private ImageView imageView;
    private Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_name,null);
        //获取资源ID
        imageView = view.findViewById(R.id.imageView);
        button = view.findViewById(R.id.finish);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //生成二维码
        creatQRCode();
        //退出登录
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                editor.clear();
                editor.commit();
            }
        });
    }

    private void creatQRCode() {
        String name = ((LoginActivity) getActivity()).getName();
        QRTask qrTask = new QRTask(getActivity(), imageView, name);
        qrTask.execute(name);
    }

    static class QRTask extends AsyncTask<String,Void,Bitmap> {
        private WeakReference<Context> mcontext;
        private WeakReference<ImageView> mimageview;

        public QRTask(Context context, ImageView imageView, String name) {
            mcontext=new WeakReference<>(context);
            mimageview=new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String str = strings[0];
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            int size = mcontext.get().getResources().getDimensionPixelOffset(R.dimen.cc);
            return QRCodeEncoder.syncEncodeQRCode(str, size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap!=null){
                mimageview.get().setImageBitmap(bitmap);
            }else{
                Toast.makeText(mcontext.get(), "生成失败", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
