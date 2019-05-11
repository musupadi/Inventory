package com.destinyapp.inventory;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.destinyapp.inventory.API.ApiRequest;
import com.destinyapp.inventory.API.RetroServer;
import com.destinyapp.inventory.Login.Login;
import com.destinyapp.inventory.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    EditText username,password;
    Button login;

    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username=(EditText)view.findViewById(R.id.etUsername);
        password=(EditText) view.findViewById(R.id.etPassword);
        login=(Button)view.findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Internet Error",Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Internet Error",Toast.LENGTH_SHORT).show();
                }else{
                    login();
                }
            }
        });
    }
    private void login (){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> login = api.getLoginAdmin(username.getText().toString(),
                password.getText().toString());
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String rss = response.body().getResponse();
                if(rss.equals("succes")){
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                }else{
                    Snackbar.make(getView(),"Username atau Password Salah Goblok", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(),"Internet Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
