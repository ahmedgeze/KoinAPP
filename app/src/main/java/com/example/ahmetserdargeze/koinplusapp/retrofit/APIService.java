package com.example.ahmetserdargeze.koinplusapp.retrofit;

import com.example.ahmetserdargeze.koinplusapp.models.Real_graphic_data;
import com.example.ahmetserdargeze.koinplusapp.models.RegisterationPojo;
import com.example.ahmetserdargeze.koinplusapp.models.Result;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinBody;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ahmetserdargeze on 08.03.2018.
 */

public interface APIService {
    @POST("register/")
    @FormUrlEncoded
    Call<RegisterationPojo> saveUser(
            @Field("username") String username,
            @Field("email")    String email,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password);


    @GET("returnTop25")
    Call<Result> getTop25();

    @GET("top25/{kur_type}/")
    Call<Result> getCoinWithType(@Path("kur_type") String kur_type);
//    void  getCoinWithType(@Path("kur_type") String kur_type, Callback<Result>cb);

    @GET("graphic/{kur_type}/{koin_type}")
    Call<Real_graphic_data> getRealTimePrice(@Path("kur_type") String kur_type, @Path("koin_type") String koin_type);

    @GET("getCoin/{kur_type}/{koin_type}/")
    Call<SingleCoinBody> getSingleCoin(@Path("kur_type") String kur_type, @Path("koin_type") String koin_type);






}
