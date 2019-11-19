package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText pwd;
    private Button bt1;
    private Button bt2;
    /**
     * 账号：
     */
    private EditText et_name;
    /**
     * 密码：
     */
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt1:
                login();
                Intent intent2 = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt2:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent,1);

                break;
        }
    }

        private void login() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.bUrl)
                    .build();

            retrofit.create(ApiService.class)
                    .login("yeyu", "123456")
                    .enqueue(new Callback<ResponseBody>() {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==2){
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            et_name.setText(name);
            et_pwd.setText(pwd);
        }
    }
}
