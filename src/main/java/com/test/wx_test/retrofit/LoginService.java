package com.test.wx_test.retrofit;

import com.test.wx_test.entity.Common;
import com.test.wx_test.entity.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("index/login")
    Call<Common<User>> login(@FieldMap Map<String,  Object> map);//登陆

    @FormUrlEncoded
    @POST("index/redister")
    Call<Common<String>> redister(@FieldMap Map<String,  Object> map);//注册

}
