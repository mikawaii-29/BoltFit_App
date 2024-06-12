package com.example.boltfit_fitnessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Page extends AppCompatActivity {

    DatabaseHelper db;
    EditText username, password;
    Button loginButton, registerButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        db = new DatabaseHelper(this);

        // Ensure these IDs match your XML layout
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (db.checkUser(user, pass)) {
                    Toast.makeText(Login_Page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Proceed to next activity if login is successful
                    startActivity(new Intent(Login_Page.this, Dashboard.class));
                } else {
                    Toast.makeText(Login_Page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

