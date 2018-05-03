package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmetserdargeze.koinplusapp.LoginorRegisterActivity;
import com.example.ahmetserdargeze.koinplusapp.MainActivity;
import com.example.ahmetserdargeze.koinplusapp.R;

/**
 * Created by ahmetserdargeze on 07.03.2018.
 */

public class LoginFragment  extends Fragment{
    View view;
    Button lf_login_button;
    TextView lf_noaccount_textview,textView1;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.loginfragment,container,false);

        textView1=(TextView)view.findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((LoginorRegisterActivity)getActivity()).loadFragment(new Graphic_Fragment(),R.id.frame_layout_container);

            }
        });



        lf_login_button=(Button) view.findViewById(R.id.lf_login_button);
        lf_noaccount_textview=(TextView) view.findViewById(R.id.lf_noaccount_textview);

        lf_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Login Fragment",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);


            }
        });

        lf_noaccount_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginorRegisterActivity)getActivity()).loadFragment(new RegisterFragment(),R.id.frame_layout_container);
            }
        });




        return view;
    }
}
