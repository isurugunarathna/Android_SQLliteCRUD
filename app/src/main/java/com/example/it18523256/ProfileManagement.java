package com.example.it18523256;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.it18523256.Databse.DBHelper;

public class ProfileManagement extends AppCompatActivity {
    EditText userName,DOB,Pswd;
    String gender;
    Button update,add;
    RadioButton male,female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        userName = findViewById(R.id.UserName_txt);
        DOB = findViewById(R.id.dob_txt);
        Pswd = findViewById(R.id.pswd_txt);
        male = findViewById(R.id.male_btn);
        female = findViewById(R.id.female_btn);
        update = findViewById(R.id.update_btn);
        add = findViewById(R.id.add_btn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                String User = userName.getText().toString();
                String date = DOB.getText().toString();
                String Password = Pswd.getText().toString();

                if (male.isChecked()) {
                    gender = "male";

                } else {
                    gender = "female";
                }
                long newRowId = dbHelper.addInfo(User,date,Password,gender);
                if(newRowId > 0) {
                    Toast.makeText(ProfileManagement.this, "User Added", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),EditProfile.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(ProfileManagement.this, "User not Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
