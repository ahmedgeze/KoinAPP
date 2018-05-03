package com.example.ahmetserdargeze.koinplusapp.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ahmetserdargeze.koinplusapp.MainActivity;
import com.example.ahmetserdargeze.koinplusapp.R;

/**
 * Created by ahmetserdargeze on 08.03.2018.
 */

public class LoginorRegisterFragment extends Fragment {
    View view;
    Button login_button,register_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.loginorregisterfragment,container,false);
        login_button=(Button) view.findViewById(R.id.login_button);
        register_button=(Button)view.findViewById(R.id.register_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"login",Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).loadFragment(new LoginFragment(),R.id.frame_layout_container);

            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"register",Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).loadFragment(new RegisterFragment(),R.id.frame_layout_container);


            }
        });

        return view;
    }



}
