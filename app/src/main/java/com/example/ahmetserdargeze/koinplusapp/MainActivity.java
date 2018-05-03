package com.example.ahmetserdargeze.koinplusapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

import com.example.ahmetserdargeze.koinplusapp.Fragments.Bottombar_fragment;
import com.example.ahmetserdargeze.koinplusapp.Fragments.Coin_fragment;
import com.example.ahmetserdargeze.koinplusapp.data.FavCoinModel;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button register_button,login_button;
    Toolbar toolbar;
    Date currentTime;
    String todayString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new Coin_fragment(),R.id.frame_Layout_Topbar);
        loadFragment(new Bottombar_fragment(),R.id.bottom_bar);







//        currentTime=Calendar.getInstance().getTime();
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
//        todayString=formatter.format(currentTime);

//        toolbar=(Toolbar) findViewById(R.id.tool_bar);
////        toolbar.setTitle(todayString.toString());
//        toolbar.setTitle(" ");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.textcolor));
//        setSupportActionBar(toolbar);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }

    public  void loadFragment(Fragment fragment, int container){
        FragmentManager fm=getFragmentManager();

        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(container,fragment);

        fragmentTransaction.commit();



    }


}
