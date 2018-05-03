package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmetserdargeze.koinplusapp.ActivityGlobal;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.models.Global_state_var;
import com.example.ahmetserdargeze.koinplusapp.models.Real_graphic_data;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmetserdargeze on 07.04.2018.
 */

public class Graphic_Fragment   extends android.support.v4.app.Fragment{

    View view;
    LineChart mChart;
    float f=10;


    public APIService graphicData;
    Real_graphic_data real_graphic_data;


    String Koin_type;
    String Kur_type;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.graphicfragment,container,false);

        if(Global_state_var.getCoin()==0)
            Kur_type="3";
        else if(Global_state_var.getCoin()==1)
            Kur_type="2";
        else if(Global_state_var.getCoin()==2)
            Kur_type="1";


        mChart=(LineChart) view.findViewById(R.id.detailGraphic);

        mChart.getDescription().setEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on favBody_usdt- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.rgb(68,132,206));

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        // add empty data
        mChart.setData(data);


        Global_state_var.setGraphicCoinName();



        ScheduledExecutorService exec= Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {


               graphicData= ApiUtils.getAPIService();
               graphicData.getRealTimePrice(Kur_type,Global_state_var.getGraphicKoinName()+"").enqueue(new Callback<Real_graphic_data>() {
                   @Override
                   public void onResponse(Call<Real_graphic_data> call, Response<Real_graphic_data> response) {
                       real_graphic_data=response.body();
                       System.out.println(real_graphic_data.getPrice()+"");
                       addEntry(real_graphic_data.getPrice());
                   }

                   @Override
                   public void onFailure(Call<Real_graphic_data> call, Throwable t) {

                   }
               });




            }


        },0,5, TimeUnit.SECONDS);





        return view;



    }





    public void fill(List<Entry> data){
        float i=1;
        int n;
        Random rnd=new Random();
        for (int j=0;j<5;j++){
            n=rnd.nextInt(100)+1;
            data.add(new Entry(i,(float)n));
            i++;
        }
    }

    public float fillLiveObject(){
       float floatNumber;
       Random rnd =new Random();
       floatNumber=(float)rnd.nextInt(15)+1;
       return floatNumber;
    }

    public void addEntry(String s){
        LineData data=mChart.getData();
        System.out.println(data.toString());
        if(data!=null){
            ILineDataSet set=data.getDataSetByIndex(0);
            if(set==null){
                set=createSet();
                data.addDataSet(set);



            }
            data.addEntry(new Entry(set.getEntryCount(),Float.parseFloat(s)),0);
            f=f+5;
            data.notifyDataChanged();

            mChart.notifyDataSetChanged();;
            mChart.setVisibleXRangeMaximum(10);
            mChart.moveViewToX(data.getEntryCount());
            mChart.invalidate();

        }

    }

    public LineDataSet createSet(){


        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(Color.rgb(58,54,68));
        set.setCircleColor(Color.rgb(107,103,125));
        set.setLineWidth(4f);
        set.setCircleRadius(4f);
        set.setFillAlpha(30);
        set.setFillColor(Color.rgb(26,60,52));
        set.setHighLightColor(Color.rgb(26, 60, 52));
        set.setValueTextColor(Color.rgb(50,43,43));
        set.setValueTextSize(12f);
        set.setDrawValues(true);
        return set;






    }




//        line=new LineDataSet(entries,"RealTime");
//        line.setColor(R.color.topBarSelected);
//        line.setValueTextColor(R.color.toolbar_button_bg);
//        line.setColor(R.color.graphicLine);
//
//        lineData=new LineData(dataSet);
//        detailChart.setData(lineData);
//        detailChart.invalidate();


}
