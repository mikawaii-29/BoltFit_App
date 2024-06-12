package com.example.boltfit_fitnessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText name, email, newUsername, newPassword;
    Button registerNewUserButton, loginacc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        loginacc = findViewById(R.id.loginAcc);
        name = findViewById(R.id.nameText);
        email = findViewById(R.id.emailText);
        newUsername = findViewById(R.id.usernameText);
        newPassword = findViewById(R.id.passwordText);
        registerNewUserButton = findViewById(R.id.loginButton);

        loginacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login_Page.class);
                startActivity(intent);
            }
        });

        registerNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userUsername = newUsername.getText().toString();
                String userPassword = newPassword.getText().toString();
                if (db.insertUser(userName, userEmail, userUsername, userPassword)) {
                    Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
