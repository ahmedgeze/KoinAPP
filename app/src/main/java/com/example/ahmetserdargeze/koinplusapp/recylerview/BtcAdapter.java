package com.example.ahmetserdargeze.koinplusapp.recylerview;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.ahmetserdargeze.koinplusapp.Fragments.Graphic_Fragment;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.data.Database;
import com.example.ahmetserdargeze.koinplusapp.data.FavCoinModel;
import com.example.ahmetserdargeze.koinplusapp.models.Global_state_var;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetserdargeze on 11.04.2018.
 */

public class BtcAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
    private ArrayList<SingleCoinResult> dataSet;

    public String color_code;
    int color;

    LinearLayout topBtc;
    LinearLayout topEth;
    LinearLayout topUsd;

    ScrollView sc;



    public BtcAdapter(ArrayList<SingleCoinResult> dataArgs){
        this.dataSet=dataArgs;

    }

    public void setDataSet(ArrayList<SingleCoinResult> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_cardview,parent,false);

        sc=(ScrollView) parent.findViewById(R.id.global_sv);


        return new SimpleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {

//        final SimpleViewHolder holder1=holder;

        SingleCoinResult coin=dataSet.get(position);
        holder.setData_favCoin(coin,position);


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
                db.openCon();


                if(db.controlWithName(s, Global_state_var.getCoin()+"")==0){
                    db.addRecord(holder.coin_name.getText().toString(),"1");
                    holder.add_fav.setImageResource(R.drawable.star_fill);

                }else {
                    db.deleteSingleRecords(s);
                    holder.add_fav.setImageResource(R.drawable.star_fill_gl);
                }


                Log.e("add_fav",holder.coin_name.getText().toString());


                db.closeCon();




            }

        });



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
