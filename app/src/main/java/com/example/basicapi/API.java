package com.example.basicapi;


import ModelResponse.LoginResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface  API {

    @FormUrlEncoded
    @POST("register.php") // replace with your endpoint path
    Call<ResponseBody> register(
            @Field("username") String name,
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("login.php") // replace with your endpoint path
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );



}
