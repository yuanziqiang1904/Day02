package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 用户名：
     */
    private EditText mEdName;
    /**
     * 密码：
     */
    private EditText mEdPwd;
    /**
     * 确认密码：
     */
    private EditText mEdPwd2;
    /**
     * 手机号：
     */
    private EditText mEdIphone;
    /**
     * 验证码：
     */
    private EditText mEdYanz;
    /**
     * 注册
     */
    private Button mBt;
    private String name;
    private String pwd;
    private String pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mEdName = (EditText) findViewById(R.id.ed_name);
        mEdPwd = (EditText) findViewById(R.id.ed_pwd);
        mEdPwd2 = (EditText) findViewById(R.id.ed_pwd2);
        mEdIphone = (EditText) findViewById(R.id.ed_iphone);
        mEdYanz = (EditText) findViewById(R.id.ed_yanz);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                name = mEdName.getText().toString().trim();
                pwd = mEdPwd.getText().toString().trim();
                pwd2 = mEdPwd2.getText().toString().trim();
                if (pwd.equals(pwd2)&&!name.isEmpty()){
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("pwd",pwd);
                    setResult(2,intent);
                    finish();
                }else{
                    Toast.makeText(this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                }
                get();
                break;
        }
    }


        private void get() {
            //步骤4：创建 Retrofit 实例
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.aUrl)//url的前缀：baseUrl
                    .build();
            //步骤5：创建 网络请求接口实例
            ApiService apiService = retrofit.create(ApiService.class);
            final Call<ResponseBody> call = apiService.getVerify();
            //异步
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.d("tag", "onResponse: " + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("tag", "onFailure: " + t.toString());
                }
            });
        }
    }
