package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmetserdargeze.koinplusapp.ActivityGlobal;
import com.example.ahmetserdargeze.koinplusapp.LoginorRegisterActivity;
import com.example.ahmetserdargeze.koinplusapp.MainActivity;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.models.LoginPojo;
import com.example.ahmetserdargeze.koinplusapp.models.RegisterationPojo;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmetserdargeze on 07.03.2018.
 */

public class LoginFragment  extends Fragment{
    View view;
    Button lf_login_button;
    TextView lf_noaccount_textview,textView1;
    EditText usernametv,passwordtv;
    APIService apiService;
    LoginPojo authToken;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.loginfragment,container,false);
        usernametv=(EditText) view.findViewById(R.id.lf_username_tv);
        passwordtv=(EditText) view.findViewById(R.id.lf_password_lf);
        apiService= ApiUtils.getAPIService();
        authToken=new LoginPojo();



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
                String username,password;
                username=usernametv.getText().toString();
                password=passwordtv.getText().toString();
                if(!(username.matches("")&& password.matches(""))){
                    apiService.login(usernametv.getText().toString(),passwordtv.getText().toString()).enqueue(new Callback<LoginPojo>() {
                        @Override
                        public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {
                            authToken=response.body();
                            if (authToken!=null){
                                Intent intent=new Intent(getActivity(), ActivityGlobal.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(getActivity(),"Invalid password or username please tryagain",Toast.LENGTH_LONG).show();

                            }


                        }

                        @Override
                        public void onFailure(Call<LoginPojo> call, Throwable t) {
                            Toast.makeText(getActivity(),"Invalid password or username please tryagain",Toast.LENGTH_LONG).show();


                        }
                    });

                }
//                Toast.makeText(getActivity(),"Login Fragment",Toast.LENGTH_LONG).show();
//                Intent intent=new Intent(getActivity(),MainActivity.class);
//                startActivity(intent);


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
