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

import java.util.List;

public class EditProfile extends AppCompatActivity {

        EditText userName,dob,pswd;
        Button edit,delete,search;
        String gender;
        RadioButton male,female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userName = findViewById(R.id.name_txt);
        dob = findViewById(R.id.dob_txt);
        pswd = findViewById(R.id.pswd_txt);
        male = findViewById(R.id.male_btn);
        female = findViewById(R.id.female_btn);
        edit = findViewById(R.id.edit_btn);
        delete = findViewById(R.id.delete_btn);
        search = findViewById(R.id.search_btn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                List users = dbHelper.readAllInfo(userName.getText().toString());

                if (users.isEmpty()){
                    Toast.makeText(EditProfile.this, "User Not Found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "User Found, User Name" + users.get(0).toString(), Toast.LENGTH_SHORT).show();
                    userName.setText(users.get(0).toString());
                    dob.setText(users.get(1).toString());
                    pswd.setText(users.get(3).toString());
                    if (users.get(2).toString().equals("Male")){
                        male.setChecked(true);
                    }
                    else {
                        female.setChecked(true);
                    }

                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                String user = userName.getText().toString();
                String date = dob.getText().toString();
                String password = pswd.getText().toString();
                if (male.isChecked()) {
                    gender = "male";

                } else {
                    gender = "female";
                }
                Boolean status = dbHelper.updateInfo(user,date,password,gender);

                if (status) {
                    Toast.makeText(EditProfile.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditProfile.this, "Updated unsuccessfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.DeleteInfo(userName.getText().toString());
                Toast.makeText(EditProfile.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();


                userName.setText(null);
                dob.setText(null);
                pswd.setText(null);
                male.setText(null);
                female.setText(null);
            }
        });

    }
}
