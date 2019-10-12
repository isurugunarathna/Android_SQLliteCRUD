package com.example.it18523256;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    EditText userName,Password;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userName = findViewById(R.id.userName_txt);
        Password =  findViewById(R.id.pswd_txt);
        login = findViewById(R.id.login_btn);
        register = findViewById(R.id.regis_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ProfileManagement.class);
                startActivity(i);
            }
        });
    }

}
