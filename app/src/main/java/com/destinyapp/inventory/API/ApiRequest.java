package com.destinyapp.inventory.API;

import com.destinyapp.inventory.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("RegisterAdmin.php")
    Call<ResponseModel> Register(@Field("username") String username,
                                         @Field("password") String password,
                                         @Field("nama_admin") String nama_admin);

    @FormUrlEncoded
    @POST("LoginAdmin.php")
    Call<ResponseModel> getLoginAdmin(@Field("username") String username,
                                      @Field("password") String password);
}
