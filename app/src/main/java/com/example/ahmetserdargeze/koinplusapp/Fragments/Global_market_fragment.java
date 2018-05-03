package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmetserdargeze.koinplusapp.ActivityGlobal;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.data.Database;
import com.example.ahmetserdargeze.koinplusapp.data.FavCoinModel;
import com.example.ahmetserdargeze.koinplusapp.models.Global_state_var;
import com.example.ahmetserdargeze.koinplusapp.models.Result;
import com.example.ahmetserdargeze.koinplusapp.models.Rv_object_coin;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinBody;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;
import com.example.ahmetserdargeze.koinplusapp.notification.MyTestService;
import com.example.ahmetserdargeze.koinplusapp.recylerview.BtcAdapter;
import com.example.ahmetserdargeze.koinplusapp.recylerview.ResultAdapter;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmetserdargeze on 18.04.2018.
 */

public class Global_market_fragment extends android.app.Fragment {
    View view;
    TextView btctv,ethtv,usdttv;
    ImageView stariv;

    RecyclerView rv;
    ResultAdapter market_rv_adapter;
    RecyclerView.LayoutManager marketLM;

    List<FavCoinModel> favcoin_list_a=new ArrayList<FavCoinModel>();

    SingleCoinBody favBody =new SingleCoinBody();
    SingleCoinResult favResult =new SingleCoinResult();
    ArrayList<SingleCoinResult> favList =new ArrayList<SingleCoinResult>();
    ArrayList<SingleCoinResult> favList_b =new ArrayList<SingleCoinResult>();
    BtcAdapter favAdaptor;



    public  APIService downloadService;
    ArrayList<Rv_object_coin> result=new ArrayList<>();
    Result response_body=new Result();

    Database db;

    Timer timer;
    TimerTask timerTask_usdt,timerTask_BTC,timerTask_ETH;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.global_market_fragment,container,false);

        rv=(RecyclerView) view.findViewById(R.id.global_market_recyclerview);
        marketLM=new LinearLayoutManager(getActivity());

        btctv=(TextView) view.findViewById(R.id.btc_tv_global);
        ethtv=(TextView) view.findViewById(R.id.eth_tv_global);
        usdttv=(TextView) view.findViewById(R.id.usdt_tv_global);
        stariv=(ImageView) view.findViewById(R.id.star_iv_global);

        usdttv.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getContext().getTheme()));


//        result=getCoin("USDT");












        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCoin("USDT");

        start();





//        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//
//// The id of the channel.
//        String id = "my_channel_01";
//
//// The user-visible name of the channel.
//        CharSequence name = getString(R.string.channel_name);
//
//// The user-visible description of the channel.
//        String description = getString(R.string.channel_description);
//
//        int importance = NotificationManager.IMPORTANCE_LOW;
//
//        NotificationChannel mChannel = new NotificationChannel(id, name,importance);
//
//// Configure the notification channel.
//        mChannel.setDescription(description);
//
//        mChannel.enableLights(true);
//// Sets the notification light color for notifications posted to this
//// channel, if the device supports this feature.
//        mChannel.setLightColor(Color.RED);
//
//        mChannel.enableVibration(true);
//        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//
//        mNotificationManager.createNotificationChannel(mChannel);
//
//        mNotificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//
//// Sets an ID for the notification, so it can be updated.
//        int notifyID = 1;
//
//// The id of the channel.
//        String CHANNEL_ID = "my_channel_01";
//
//// Create a notification and set the notification channel.
//        Notification notification = new Notification.Builder(getContext(),CHANNEL_ID)
//                .setContentTitle("New Message")
//                .setContentText("You've received new messages.")
//                .setSmallIcon(R.drawable.alarm_new)
//                .setChannelId(CHANNEL_ID)
//                .build();
//
//// Issue the notification.
//        mNotificationManager.notify(3, notification);











        usdttv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setCoin(0);
                changeTopColor(0);
                switch (Global_state_var.getFav_state()){
                    case 0:
//                        getCoin("USDT");
                        getCoinWithThread();
                        break;
                    case 1:
                        setFavRv(0+"","USDT");
                        break;








                }



            }
        });

        btctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setCoin(1);
                changeTopColor(1);
                switch (Global_state_var.getFav_state()){
                    case 0:
//                        getCoin("BTC");
                        getCoinWithThread();
                        break;
                    case 1:
                        setFavRv(1+"","BTC");
                        break;



                }
        }});



        ethtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global_state_var.setCoin(2);
                changeTopColor(2);
                switch (Global_state_var.getFav_state()){
                    case 0:
//                        getCoin("ETH");
                        getCoinWithThread();
                        break;
                    case 1:
                        setFavRv(2+"","ETH");
                        break;



                }

            }
        });

        stariv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Global_state_var.getFav_state()){
                    case 0:

                        Global_state_var.setCoin(0);
                        stariv.setImageBitmap(null);
                        starChange(0);
                        changeTopColor(0);
                        stariv.invalidate();
                        setFavRv(0+"","USDT");
//                        getCoinLater(0+"");
                        break;
                    case 1:
                        Global_state_var.setCoin(0);
                        changeTopColor(0);
                        starChange(1);
                        stariv.invalidate();
//                        getCoin("USDT");
//                        setFavRv(0+"","USDT");
                        getCoinLater("USDT");


                        break;



                }



            }
        });


    }

    public void changeTopColor(int active){
        switch (active){
            case 0:
                usdttv.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getContext().getTheme()));
                ethtv.setBackgroundColor(getResources().getColor(R.color.layout_bg,getContext().getTheme()));
                btctv.setBackgroundColor(getResources().getColor(R.color.layout_bg,getContext().getTheme()));
                Log.i("Coin usdt",Global_state_var.getCoin()+"");
                break;
            case 1:
                usdttv.setBackgroundColor(getResources().getColor(R.color.layout_bg,getContext().getTheme()));
                ethtv.setBackgroundColor(getResources().getColor(R.color.layout_bg,getContext().getTheme()));
                btctv.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getContext().getTheme()));
                Log.i("Coin BTC",Global_state_var.getCoin()+"");
                break;
            case 2:
                usdttv.setBackgroundColor(getResources().getColor(R.color.layout_bg,getContext().getTheme()));
                ethtv.setBackgroundColor(getResources().getColor(R.color.topBarSelected,getContext().getTheme()));
                btctv.setBackgroundColor(getResources().getColor(R.color.layout_bg,getContext().getTheme()));
                Log.i("Coin ETH",Global_state_var.getCoin()+"");
                break;




        }

    }


    public void starChange(int state){
        switch (state){
            case 0:
                stariv.setImageBitmap(null);
                stariv.setBackgroundResource(R.drawable.star_fill_gl);
                Global_state_var.setFav_state(1);
                Log.i("active stat",Global_state_var.getFav_state()+"");

                break;

            case 1:
                stariv.setImageBitmap(null);
                stariv.setBackgroundResource(R.drawable.starnew_gl);
                Global_state_var.setFav_state(0);
                Log.i("deactive stat",Global_state_var.getFav_state()+"");


                break;

        }


    }

    public ArrayList<Rv_object_coin> getCoin(final String kur){
        Global_state_var.setThreadCount();


//        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(1);
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
                downloadService=ApiUtils.getAPIService();
                downloadService.getCoinWithType(kur).enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {

                        response_body=response.body();
                        result=response_body.getResult();
                        market_rv_adapter=new ResultAdapter(result,view.getContext());


                        rv.setLayoutManager(marketLM);
                        rv.setItemAnimator(new DefaultItemAnimator());
//                rv.setAdapter(market_rv_adapter);
                        rv.swapAdapter(market_rv_adapter,true);
//                rv.getAdapter().notifyDataSetChanged();
                        rv.invalidate();


                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.i("HATA","BEKLENMEYEN HATA"+t);

                    }
                });


//            }
//        },0,6, TimeUnit.SECONDS);



        return result;
    }

    public ArrayList<Rv_object_coin> getCoinLater(String kur){


        downloadService=ApiUtils.getAPIService();
        downloadService.getCoinWithType(kur).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(favList!=null||favList.size()>0){
                    favList.clear();
                    rv.getAdapter().notifyDataSetChanged();
                }
                response_body=response.body();
                result=response_body.getResult();

                market_rv_adapter=new ResultAdapter(result,view.getContext());


                rv.setLayoutManager(marketLM);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(market_rv_adapter);
                rv.invalidate();



            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.i("HATA","BEKLENMEYEN HATA"+t);

            }
        });


        return result;
    }

    public ArrayList<Rv_object_coin> getCoinWithThread(){

    timerTask_usdt=new TimerTask() {
        @Override
        public void run() {
            getCoin("USDT");
        }
    };

    timerTask_ETH=new TimerTask() {
        @Override
        public void run() {
            getCoin("ETH");
        }
    };

    timerTask_BTC=new TimerTask() {
        @Override
        public void run() {
            getCoin("BTC");
        }
    };

    if (Global_state_var.getCoin()==0){
        if (Global_state_var.getThreadCount_BTC()!=0 ) {
            stop();
            System.out.println(Global_state_var.getThreadCount_BTC());

        }

            Global_state_var.setThreadCount_BTC();
            start(timerTask_usdt);

    }

        if (Global_state_var.getCoin()==1){
            if (Global_state_var.getThreadCount_BTC()!=0) {
                stop();
            }
            Global_state_var.setThreadCount_BTC();

            start(timerTask_BTC);


        }

        if (Global_state_var.getCoin()==2){
            if (Global_state_var.getThreadCount_BTC()!=0) {
                stop();
            }
            Global_state_var.setThreadCount_BTC();

            start(timerTask_ETH);


        }



        return result;
    }

    public void start(TimerTask timerTask) {
        if(timer != null) {
            return;
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 2000);
    }

    public void stop() {
        timer.cancel();
        timer = null;
    }



    public ArrayList<SingleCoinResult> setFavRv(String kur,String kur_name){
        favcoin_list_a=setFavList(kur);//0 1 2
        favList.clear();


        for (int i=0;i<favcoin_list_a.size();i++){
            downloadService=ApiUtils.getAPIService();
            downloadService.getSingleCoin(kur_name,favcoin_list_a.get(i).getKoin_name()+"").enqueue(new Callback<SingleCoinBody>() {
                @Override
                public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                    if (result!=null && result.size()>0){
                        result.clear();
                        rv.getAdapter().notifyDataSetChanged();
                    }

                    favBody=response.body();
                    favResult=favBody.getSingleCoinResult();
                    favList.add(favResult);
                    favList_b=favList;
//                    result.clear();
//                  rv.getAdapter().notifyDataSetChanged();
                    favAdaptor=new BtcAdapter(favList);
                    rv.swapAdapter(favAdaptor,true);
//                    rv.setAdapter(favAdaptor);
                    rv.getAdapter().notifyDataSetChanged();
                    rv.invalidate();




                }

                @Override
                public void onFailure(Call<SingleCoinBody> call, Throwable t) {

                }
            });//kurname=usdt,btc,sasad



        }
        for (int j=0;j<favList.size();j++)
            Log.i("abc",favList.get(j).getKoinIdKoinName()+favList.get(j).getLast());
        return favList_b;

    }





    public List<FavCoinModel> setFavList(String kur){
        db= new Database(view.getContext());
        db.openCon();
        favcoin_list_a=db.ListByKurId(kur);
        db.closeCon();
        for (int i=0;i<favcoin_list_a.size();i++)
            Log.i("Koin",favcoin_list_a.get(i).getKoin_name()+favcoin_list_a.get(i).getKur());

        return  favcoin_list_a;

    }

    public void  start(){
        AlarmManager manager=(AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent=new Intent(getContext(),MyTestService.class);
        PendingIntent pendingIntent=PendingIntent.getService(getContext(),0,myIntent,0);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(),500,pendingIntent);

    }




}
