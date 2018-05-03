package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmetserdargeze.koinplusapp.LoginorRegisterActivity;
import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.models.RegisterationPojo;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ahmetserdargeze on 07.03.2018.
 */

public class RegisterFragment extends Fragment {
    View view ;
    Button rf_register_button;
    EditText username,email,password,confirmpassword;
    private APIService mAPIService;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.registerfragment,container,false);

        username=(EditText) view.findViewById(R.id.rf_username_edittext);
        email=(EditText) view.findViewById(R.id.rf_email_edittext);
        password=(EditText) view.findViewById(R.id.rf_password_edittext);
        confirmpassword=(EditText) view.findViewById(R.id.rf_confirmpassword_edittext);

        mAPIService= ApiUtils.getAPIService();

        rf_register_button=(Button) view.findViewById(R.id.rf_register_button);
        rf_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname=username.getText().toString().trim();
                String mail=email.getText().toString().trim();
                String pw=password.getText().toString().trim();
                String cpw=confirmpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(uname)&&!TextUtils.isEmpty(mail)&&!TextUtils.isEmpty(pw)&&!TextUtils.isEmpty(cpw)){
                    mAPIService.saveUser(uname,mail,pw,cpw).enqueue(new Callback<RegisterationPojo>() {
                        @Override
                        public void onResponse(Call<RegisterationPojo> call, Response<RegisterationPojo> response) {
                            Log.i("response",response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<RegisterationPojo> call, Throwable t) {

                        }
                    });

                }



                ((LoginorRegisterActivity)getActivity()).loadFragment(new LoginFragment(),R.id.frame_layout_container);
                Toast.makeText(getActivity(), "Confirm your account via the inbound link to your mail", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }









}
