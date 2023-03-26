package com.example.basicapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class MainActivity extends AppCompatActivity  {

    TextView loginLink;
    EditText name, email, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       

        name=findViewById(R.id.etName);
        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etPassword);
        register=findViewById(R.id.btnRegister);
        loginLink=findViewById(R.id.textBtn_login);

        //to hide action bar
        getSupportActionBar().hide();

        //to hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registerUser();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });

}

private void registerUser(){

        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(userName,userEmail,userPassword);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                ResponseBody  registerResponse=response.body();
                 if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, registerResponse.toString(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, registerResponse.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
}
}