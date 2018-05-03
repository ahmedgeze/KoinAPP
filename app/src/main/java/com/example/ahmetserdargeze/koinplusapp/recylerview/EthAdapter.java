package com.example.ahmetserdargeze.koinplusapp.recylerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.data.Database;
import com.example.ahmetserdargeze.koinplusapp.models.Rv_object_coin;

import java.util.ArrayList;

/**
 * Created by ahmetserdargeze on 11.04.2018.
 */

public class EthAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private ArrayList<Rv_object_coin> dataSet;

    public String color_code;
    int color;

    LinearLayout topBtc;
    LinearLayout topEth;
    LinearLayout topUsd;



    public EthAdapter(ArrayList<Rv_object_coin> dataArgs){
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
                Log.e("gotochart","ALLLLLLLLLLLLLLLARM");
                Database db=new Database(holder.go_to_chart.getContext());
                db.openCon();
                Log.e("db",db.VerileriListele().toString());
                db.closeCon();


            }
        });

        holder.add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=holder.coin_name.getText().toString();
                Database db=new Database(holder.add_fav.getContext());
                db.openCon();


                if(db.controlWithName(s,"2")==0){
                    db.addRecord(holder.coin_name.getText().toString(),"2");
                    holder.add_fav.setImageResource(R.drawable.star_fill);

                }else {
                    db.deleteSingleRecords(s);
                    holder.add_fav.setImageResource(R.drawable.star_fill_gl);
                }


                Log.e("add_fav",holder.coin_name.getText().toString());


                db.closeCon();




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
