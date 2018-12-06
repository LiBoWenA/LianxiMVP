package com.example.lixanximvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lixanximvp.Bean.Bean;
import com.example.lixanximvp.Persenter.IPersenter;
import com.example.lixanximvp.Persenter.IPersenterImpl;
import com.example.lixanximvp.View.IView;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private EditText et_name,et_pass;
    private CheckBox ck_login,ck_pass;
    IPersenterImpl iPersenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String path = "http://www.xieast.com/api/user/login.php?username=%s&password=%s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPersenter = new IPersenterImpl(this);
        init();
    }

    private void init() {
        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pass);
        ck_login = findViewById(R.id.login);
        ck_pass = findViewById(R.id.pwd);
        findViewById(R.id.button).setOnClickListener(this);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //记住密码
        boolean j_ck = sharedPreferences.getBoolean("j_ck",false);
        if(j_ck){
            String name = sharedPreferences.getString("name", null);
            String pass = sharedPreferences.getString("pass", null);
            et_name.setText(name);
            et_pass.setText(pass);
        }

        //自动登录
        boolean z_ck = sharedPreferences.getBoolean("z_ck",false);
        if(z_ck){
            String name = et_name.getText().toString();
            String pass = et_pass.getText().toString();
            ck_login.setChecked(true);
            iPersenter.showRequestData(String.format(path,name,pass),null,Bean.class);
        }
        //点击自动登录自动勾选记住密码
        ck_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ck_pass.setChecked(true);
                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.button:
                String name = et_name.getText().toString();
                String pass = et_pass.getText().toString();

                if (ck_pass.isChecked()){
                    editor.putString("name",name);
                    editor.putString("pass",pass);

                    editor.putBoolean("j_ck",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }

                if (ck_login.isChecked()){
                    editor.putBoolean("z_ck",true);
                    editor.commit();
                }
                Bean.DataBean bean = new Bean.DataBean();
                bean.setName(name);
                bean.setMobile(pass);
                iPersenter.showRequestData(String.format(path,name,pass),null,Bean.class);
                break;
              default:
                  break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.onDestory();
    }


    @Override
    public void startRequestData(Object data) {
        Bean bean = (Bean) data;
        if(bean.getCode() == 100){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.putExtra("name",bean.getData().getName());
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,bean.getMsg().toString(),Toast.LENGTH_SHORT).show();
        }
    }

}
