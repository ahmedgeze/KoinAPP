package com.example.ahmetserdargeze.koinplusapp;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.LinearLayout;

import com.example.ahmetserdargeze.koinplusapp.Fragments.Global_market_fragment;
import com.example.ahmetserdargeze.koinplusapp.models.Global_state_var;
import com.example.ahmetserdargeze.koinplusapp.models.Result;
import com.example.ahmetserdargeze.koinplusapp.notification.MyTestService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmetserdargeze on 18.04.2018.
 */

public class ActivityGlobal extends AppCompatActivity {


    LinearLayout marketll,profilell,tradell,settingsll;

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("http://192.168.1.9:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();


//    public void startAlarm(Context context){
//        AlarmManager manager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent myIntent;
//        PendingIntent pendingIntent;
//
//        myIntent=new Intent(getApplicationContext(),AlarmBroadcastService.class);
//        pendingIntent=PendingIntent.getBroadcast(context,0,myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),500,pendingIntent);
//        Log.d("ME","ALARM STARTED");

//    }

    public void changeColorBottom(int active_bottom){
        switch (active_bottom){
            case 0:
                marketll.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getApplicationContext().getTheme()));
                profilell.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                tradell.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                settingsll.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                loadFragment(new Global_market_fragment(),R.id.global_container);
                break;
            case 1:
                marketll.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                profilell.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getApplicationContext().getTheme()));
                tradell.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                settingsll.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                break;
            case 2:
                marketll.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                profilell.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                tradell.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getApplicationContext().getTheme()));
                settingsll.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                break;

            case 3:
                marketll.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                profilell.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                tradell.setBackgroundColor(getResources().getColor(R.color.layout_bg,getApplicationContext().getTheme()));
                settingsll.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getApplicationContext().getTheme()));
                break;


        }



    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

//        int NOTIFICATION_ID = 234;
//
//        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//
//
//            String CHANNEL_ID = "my_channel_01";
//            CharSequence name = "my_channel";
//            String Description = "This is my channel";
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//            mChannel.setDescription(Description);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mChannel.setShowBadge(false);
//            notificationManager.createNotificationChannel(mChannel);
//        }
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"my_channel_01")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("sa")
//                .setContentText("asd");
//
//        Intent resultIntent = new Intent(getApplicationContext(), ActivityGlobal.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
//        stackBuilder.addParentStack(ActivityGlobal.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        builder.setContentIntent(resultPendingIntent);
//
//        notificationManager.notify(NOTIFICATION_ID, builder.build());













        marketll=(LinearLayout) findViewById(R.id.market_button);
        profilell=(LinearLayout) findViewById(R.id.account_button);
        tradell=(LinearLayout) findViewById(R.id.trade_button);
        settingsll=(LinearLayout) findViewById(R.id.settings_button);


        changeColorBottom(Global_state_var.getBottombar_state());

        marketll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setBottombar_state(0);
                changeColorBottom(Global_state_var.getBottombar_state());
            }
        });

        profilell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setBottombar_state(1);
                changeColorBottom(Global_state_var.getBottombar_state());
            }
        });

        tradell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setBottombar_state(2);
                changeColorBottom(Global_state_var.getBottombar_state());
            }
        });

        settingsll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setBottombar_state(3);
                changeColorBottom(Global_state_var.getBottombar_state());
            }
        });






    }
























    public  void loadFragment(Fragment fragment, int container){
        FragmentManager fm=getFragmentManager();

        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(container,fragment);

        fragmentTransaction.commit();



    }
}
