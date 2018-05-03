package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ahmetserdargeze.koinplusapp.R;

/**
 * Created by ahmetserdargeze on 28.03.2018.
 */

public class Bottombar_fragment extends Fragment {
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.toolbar,container,false);


        return view;
    }
}
