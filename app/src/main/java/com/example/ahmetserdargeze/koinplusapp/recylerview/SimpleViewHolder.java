package com.example.ahmetserdargeze.koinplusapp.recylerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.data.Database;
import com.example.ahmetserdargeze.koinplusapp.data.FavCoinModel;
import com.example.ahmetserdargeze.koinplusapp.models.Global_state_var;
import com.example.ahmetserdargeze.koinplusapp.models.Rv_object_coin;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;

/**
 * Created by ahmetserdargeze on 27.03.2018.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Context context;
    ImageView coin_pic,alarm,add_fav,go_to_chart,exchange;
    TextView coin_name,coin_price,volume,change;
    LinearLayout parent,child;

    Button calculator;
    TextView dialog_kur,dialog_koin,dialog_result;






    public SimpleViewHolder(View itemView) {
        super(itemView);
        context=itemView.getContext();

        coin_pic=(ImageView) itemView.findViewById(R.id.coin_pic);

        coin_name=(TextView) itemView.findViewById(R.id.coin_name);
        coin_price=(TextView) itemView.findViewById(R.id.coin_price);
        volume=(TextView) itemView.findViewById(R.id.volume);
        change=(TextView) itemView.findViewById(R.id.change);

        add_fav=(ImageView) itemView.findViewById(R.id.add_fav);
        go_to_chart=(ImageView) itemView.findViewById(R.id.go_to_chart);
        alarm=(ImageView) itemView.findViewById(R.id.alarm);
        exchange=(ImageView) itemView.findViewById(R.id.exchange_rate);


        parent=(LinearLayout) itemView.findViewById(R.id.parentLL);
        child=(LinearLayout) itemView.findViewById(R.id.rv_child_items);





        for(int i=0;i<90;i++){
//            chart.setPadding(0,20,0,20);
//            chart.setBackground(ContextCompat.getDrawable(context,R.color.toolbar_bg));
            parent.setOnClickListener(this);

        }

    }
    public  void setDataa(Rv_object_coin clickedObject,int position){
        Database db=new Database(this.coin_name.getContext());
        String kurname=clickedObject.getKurIdKurName();
        switch (kurname){
            case "USDT":
                kurname="0";
                break;
            case "ETH":
                kurname="2";
                break;
            case "BTC":
                kurname="1";
                break;
        }

//        db.openCon();
//        int i=db.controlWithName(clickedObject.getKoinIdKoinName(),Global_state_var.getCoin()+"");
//        db.closeCon();
//
//        if(i==1){
//            this.add_fav.setImageResource(R.drawable.star_fill);
//        }else {
//            this.add_fav.setImageResource(R.drawable.star_new);
//        }


        this.coin_name.setText(clickedObject.getKoinIdKoinName()+"");
        this.coin_price.setText(clickedObject.getLast().substring(0,8)+" "+clickedObject.getKurIdKurName());
        this.change.setText("%"+clickedObject.getChange().substring(0,4)+"");
        this.volume.setText(clickedObject.getVolume().substring(0,8)+"");
        this.coin_pic.setImageResource(R.drawable.btc_t);
        this.alarm.setImageResource(R.drawable.alarm_new);
        this.child.setVisibility(View.GONE);


    }

    public void  setData_favCoin(SingleCoinResult singleCoinResult,int position){

        Database db=new Database(this.coin_name.getContext());
//        String kurname=singleCoinResult.getKurIdKurName();
        String kur_name=singleCoinResult.getKurIdKurName();
//        String kurname="USDT";
        switch (kur_name){
            case "USDT":
                kur_name="1";
                break;
            case "ETH":
                kur_name="3";
                break;
            case "BTC":
                kur_name="2";
                break;
        }

        db.openCon();
        int i=db.controlWithName(singleCoinResult.getKoinIdKoinName(), Global_state_var.getCoin()+"");
        db.closeCon();
        if(i == 1){
            this.add_fav.setImageResource(R.drawable.star_fill);
        }
        else {
            this.add_fav.setImageResource(R.drawable.star_new);
        }



        this.coin_name.setText(singleCoinResult.getKoinIdKoinName()+"");
        this.coin_price.setText(singleCoinResult.getLast().substring(0,7)+" "+singleCoinResult.getKurIdKurName());
        this.change.setText("%"+singleCoinResult.getChange().substring(0,3));
        this.volume.setText(singleCoinResult.getVolume().substring(0,8)+"");
        this.coin_pic.setImageResource(R.drawable.btc_t);
        this.alarm.setImageResource(R.drawable.alarm_new);
        this.child.setVisibility(View.GONE);






    }

    @Override
    public void onClick(View v) {
        Log.i("visibled","görünür oldu");

        if(v.getId()==R.id.parentLL){
            if(child.getVisibility()==View.VISIBLE){
                child.setVisibility(View.GONE);
                Log.i("visibled"," oldu");
                FavCoinModel.setKoin__name(this.coin_name.getText().toString());
                Log.i("visibled",FavCoinModel.getKoin__name().toString());

            }
            else {
                child.setVisibility(View.VISIBLE);
                Log.i("visibled","görünür oldu");
                FavCoinModel.setKoin__name(this.coin_name.getText().toString());
                Log.i("visibled",FavCoinModel.getKoin__name().toString());
            }

        }



    }
}


