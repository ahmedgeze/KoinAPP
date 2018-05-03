package com.example.ahmetserdargeze.koinplusapp.notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ahmetserdargeze.koinplusapp.ActivityGlobal;
import com.example.ahmetserdargeze.koinplusapp.Fragments.Global_market_fragment;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinBody;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ahmetserdargeze on 26.04.2018.
 */

public class MyTestService extends IntentService {
    APIService download;
    SingleCoinResult singleCoinResult=new SingleCoinResult();
    SingleCoinBody singleCoinBody=new SingleCoinBody();
    // Must create a default constructor
    public MyTestService() {
        // Used to name the worker thread, important only for debugging.
        super("MyTes");
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("start","trihger");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("RETR","TRİGGER");
        download= ApiUtils.getAPIService();


        download.getSingleCoin("USDT","BTC").enqueue(new Callback<SingleCoinBody>() {
            @Override
            public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                singleCoinBody=response.body();
                singleCoinResult=singleCoinBody.getSingleCoinResult();

                NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

// The id of the channel.
                String id = "my_channel_01";

// The user-visible name of the channel.
                CharSequence name = getString(R.string.channel_name);

// The user-visible description of the channel.
                String description = getString(R.string.channel_description);

                int importance = NotificationManager.IMPORTANCE_LOW;

                NotificationChannel mChannel = new NotificationChannel(id, name,importance);

// Configure the notification channel.
                mChannel.setDescription(description);

                mChannel.enableLights(true);
// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
                mChannel.setLightColor(Color.RED);

                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

                mNotificationManager.createNotificationChannel(mChannel);

                mNotificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

// Sets an ID for the notification, so it can be updated.
                int notifyID = 1;

// The id of the channel.
                String CHANNEL_ID = "my_channel_01";

// Create a notification and set the notification channel.
                Notification notification = new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                        .setContentTitle(singleCoinResult.getKoinIdKoinName())
                        .setContentText(singleCoinResult.getLast())
                        .setSmallIcon(R.drawable.alarm_new)
                        .setChannelId(CHANNEL_ID)
                        .build();

// Issue the notification.
                mNotificationManager.notify(3, notification);







            }

            @Override
            public void onFailure(Call<SingleCoinBody> call, Throwable t) {

            }
        });


        // This describes what will happen when service is triggered
    }
}