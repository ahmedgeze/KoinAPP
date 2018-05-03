package com.example.ahmetserdargeze.koinplusapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmetserdargeze.koinplusapp.Fragments.LoginFragment;

public class LoginorRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginor_register);
        loadFragment(new LoginFragment(),R.id.frame_layout_container);
    }

    public  void loadFragment(Fragment fragment,int container){
        FragmentManager fm=getFragmentManager();

        FragmentTransaction fragmentTransaction=fm.beginTransaction();

        fragmentTransaction.replace(container,fragment);

        fragmentTransaction.commit();



    }
}
