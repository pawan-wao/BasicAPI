package com.example.basicapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ModelResponse.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {



    TextView regLink;
    EditText email, password;
    Button login;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regLink=findViewById(R.id.textBtn_Reg);
        login=findViewById(R.id.btnLogin);
        email=findViewById(R.id.laEmail);
        password=findViewById(R.id.laPassword);

        //registration link work
        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(login.this, MainActivity.class);
            }
        });

        //on click of login Button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                Toast.makeText(login.this, "Login Button Clicked", Toast.LENGTH_SHORT).show();
                Intent login_home = new Intent (login.this, HomeActivity.class);
                startActivity(login_home);
            }
        });



    }

    public void userLogin(){

        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .login(userEmail,userPassword);
       call.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

           LoginResponse loginResponse=response.body();

           if(response.isSuccessful()){

               Toast.makeText(login.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
               Intent intent = new Intent (login.this, HomeActivity.class);
               startActivity(intent);
           }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {

               Toast.makeText(login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

           }
       });


    }
}