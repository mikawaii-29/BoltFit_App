package com.example.boltfit_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Page extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText username, password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initializeViews();
        db = new DatabaseHelper(this);

        setupLoginButton();
    }

    private void initializeViews() {
        username = findViewById(R.id.usernameText1);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton1);
    }

    private void setupLoginButton() {
        loginButton.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(Login_Page.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (db.checkUser(user, pass)) {
                Toast.makeText(Login_Page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_Page.this, Dashboard.class);
                intent.putExtra("username", username.getText().toString().trim());
                startActivity(intent);
            } else {
                Toast.makeText(Login_Page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



