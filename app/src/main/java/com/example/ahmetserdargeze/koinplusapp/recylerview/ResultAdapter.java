package com.example.ahmetserdargeze.koinplusapp.recylerview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmetserdargeze.koinplusapp.ActivityGlobal;
import com.example.ahmetserdargeze.koinplusapp.Fragments.Global_market_fragment;
import com.example.ahmetserdargeze.koinplusapp.Fragments.Graphic_Fragment;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.data.Database;
import com.example.ahmetserdargeze.koinplusapp.data.FavCoinModel;
import com.example.ahmetserdargeze.koinplusapp.models.Global_state_var;
import com.example.ahmetserdargeze.koinplusapp.models.Rv_object_coin;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetserdargeze on 27.03.2018.
 */

public class ResultAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    Button calculator;
    TextView dialog_kur,dialog_koin,dialog_result;
    ImageView dialog_exit;

    private ArrayList<Rv_object_coin> dataSet;

    public String color_code;
    int color;

    LinearLayout topBtc;
    LinearLayout topEth;
    LinearLayout topUsd;

    private Context activity;



    public ResultAdapter(ArrayList<Rv_object_coin> dataArgs){
        this.dataSet=dataArgs;

    }

    public ResultAdapter(ArrayList<Rv_object_coin> dataArgs,Context activity){
        this.activity=activity;
        this.dataSet=dataArgs;

    }

    public void setDataSet(ArrayList<Rv_object_coin> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_cardview,parent,false);

        topUsd=(LinearLayout) parent.findViewById(R.id.topUsdtLay);
        topEth=(LinearLayout) parent.findViewById(R.id.topEthLay);
        topBtc=(LinearLayout) parent.findViewById(R.id.topBtcLay);




        return new SimpleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {

//        final SimpleViewHolder holder1=holder;

        Rv_object_coin coin=dataSet.get(position);
        holder.setDataa(coin,position);


        holder.alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("alarmlistener","ALLLLLLLLLLLLLLLARM");
                Database db=new Database(holder.alarm.getContext());
                db.openCon();
                db.deleteAllRecords();
                db.closeCon();


//

            }
        });


        holder.go_to_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Global_state_var.setGraphicKoinName(holder.coin_name.getText().toString());
//
//                Log.e("gotochart","ALLLLLLLLLLLLLLLARM");
//                Database db=new Database(holder.go_to_chart.getContext());
//                db.openCon();
//                List<FavCoinModel> x=new ArrayList<>();
//                x=db.VerileriListele();
//                for(int i=0;i<x.size();i++){
//                    Log.i(i+"",x.get(i).getKoin_name()+x.get(i).getKur()+"");
//                }
//                db.closeCon();

                AppCompatActivity activity = (AppCompatActivity) holder.go_to_chart.getContext();
                Graphic_Fragment g=new Graphic_Fragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.global_container,g).commit();



            }
        });

        holder.add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=holder.coin_name.getText().toString();
                Database db=new Database(holder.add_fav.getContext());
//                int kur=FavCoinModel.getKur_state();
                int kur= Global_state_var.getCoin();
                db.openCon();


                if(db.controlWithName(s,kur+"")==0){
                    db.addRecord(holder.coin_name.getText().toString(),kur+"");
                    holder.add_fav.setImageResource(R.drawable.star_fill);

                }else if(db.controlWithName(s,kur+"")>0){
                    db.deleteSingleRecords(s);
                    holder.add_fav.setImageResource(R.drawable.star_fill_gl);

                }
                else {
                    Log.i("db error","hata");

                }


                Log.e("add_fav",holder.coin_name.getText().toString()+kur);


                db.closeCon();





            }

        });

        holder.exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kur="";
                final Dialog dialog=new Dialog(activity);
                dialog.setContentView(R.layout.exchange_rate);
                dialog.setTitle("Exchange Rate Calculator");

                calculator=(Button) dialog.findViewById(R.id.custom_calculate);
                dialog_koin=(TextView) dialog.findViewById(R.id.ex_dialog_coin);
                dialog_kur=(TextView) dialog.findViewById(R.id.ex_dialog_kur);
                dialog_result=(TextView) dialog.findViewById(R.id.ex_dialog_result);
                dialog_exit=(ImageView) dialog.findViewById(R.id.custom_exit);

                dialog_koin.setHint(holder.coin_name.getText());




                Window window=dialog.getWindow();
                window.setGravity(Gravity.CENTER|Gravity.TOP);
                WindowManager.LayoutParams lp=window.getAttributes();


                lp.x=100;
                lp.y=100;
                lp.width=1000;
                lp.height=1000;
                lp.alpha=0.9f;

                window.setAttributes(lp);


                switch (Global_state_var.getCoin()){
                    case 0:
                        dialog_kur.setText("USDT");
                        break;

                    case 1:
                        dialog_kur.setText("BTC");
                        break;

                    case 2:
                        dialog_kur.setText("ETH");
                        break;



                }

                calculator.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double quantity,dialog_price,exchange_rate;
                        quantity=Double.parseDouble(dialog_koin.getText().toString());
                        dialog_price=Double.parseDouble(holder.coin_price.getText().toString().substring(0,8));
                        exchange_rate=quantity*dialog_price;
                        dialog_result.setText(dialog_koin.getText().toString()+holder.coin_name.getText()+" " +exchange_rate+" "+dialog_kur.getText());
                        dialog_result.setVisibility(View.VISIBLE);
                    }
                });

                dialog_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();


                    }
                });





                dialog.show();







            }
        });







//        holder1.coin_name.setText(dataSet.get(position).getKoinIdKoinName()+"");
//        holder1.coin_price.setText(dataSet.get(position).getLast()+"");
//        holder1.change.setText(dataSet.get(position).getChange()+"");
//        holder1.volume.setText(dataSet.get(position).getVolume()+"");
//        holder1.kur.setText(dataSet.get(position).getKurIdKurName()+"");
//        holder1.baseVolume.setText(dataSet.get(position).getBaseVolume()+"");
//        holder1.time.setText(dataSet.get(position).getTime());
//        holder1.low.setText(dataSet.get(position).getLow());
//        holder1.coin_pic.setImageResource(R.drawable.btc_t);
//        holder1.alarm.setImageResource(R.drawable.if_alarm_408410);


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }










}



