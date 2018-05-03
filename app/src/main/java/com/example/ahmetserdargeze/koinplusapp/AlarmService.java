package com.example.ahmetserdargeze.koinplusapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinBody;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by ahmetserdargeze on 26.04.2018.
 */

public class AlarmService extends Service{
    APIService download;
    SingleCoinBody body=new SingleCoinBody();
    SingleCoinResult result=new SingleCoinResult();



    @Override
    public void onCreate() {
        Log.i("START SERVİCE","STARTED");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started", LENGTH_LONG).show();
        download= ApiUtils.getAPIService();
        download.getSingleCoin("USDT","BTC").enqueue(new Callback<SingleCoinBody>() {
            @Override
            public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                body=response.body();
                result=body.getSingleCoinResult();
                Log.i("BTC",result.getKoinIdKoinName());


            }

            @Override
            public void onFailure(Call<SingleCoinBody> call, Throwable t) {

            }
        });
        Log.i("START SERVİCE","STARTED");


        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        Toast.makeText(this,"Service Destroyed...", LENGTH_LONG).show();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
