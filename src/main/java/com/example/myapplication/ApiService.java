package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //验证码
    String aUrl="http://yun918.cn/study/public/index.php/";
    @GET("verify")
    Call<ResponseBody> getVerify();

      //登录
     String bUrl="http://yun918.cn/study/public/index.php/";
    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("username") String name,
                             @Field("password") String psd);


}
