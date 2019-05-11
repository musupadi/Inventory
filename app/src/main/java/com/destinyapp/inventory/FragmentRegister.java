package com.destinyapp.inventory;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.destinyapp.inventory.API.ApiRequest;
import com.destinyapp.inventory.API.RetroServer;
import com.destinyapp.inventory.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegister extends Fragment {

    EditText username,password,nama;
    Button register;

    public FragmentRegister() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username=(EditText)view.findViewById(R.id.etUsername);
        password=(EditText)view.findViewById(R.id.etPassword);
        nama=(EditText)view.findViewById(R.id.etNama);

        register=(Button)view.findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"USERNAME NYA ISI GOBLOK",Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"ISI PASSOWORDNYA GOBLOK",Toast.LENGTH_LONG).show();
                }else if(nama.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"ISI NAMANYA GOBLOK",Toast.LENGTH_LONG).show();
                }else{
                    register();
                }
            }
        });
    }
    private void register(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Register = api.Register(username.getText().toString(),
                password.getText().toString(),
                nama.getText().toString());
        Register.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String Response = response.body().getResponse();
                if (Response.equals("Update")){
                    Toast.makeText(getActivity(),"username sudah digunakan",Toast.LENGTH_LONG).show();
                }else if (Response.equals("Insert")){
                    Toast.makeText(getActivity(),"berhasil",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"data error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(),"Internet Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
