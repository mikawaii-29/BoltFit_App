package com.example.boltfit_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText name, email, newUsername, newPassword;
    private Button registerNewUserButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        db = new DatabaseHelper(this);

        setupLoginButton();
        setupRegisterButton();
    }

    private void initializeViews() {
        loginButton = findViewById(R.id.loginAcc);
        name = findViewById(R.id.nameText);
        email = findViewById(R.id.usernameText1);
        newUsername = findViewById(R.id.usernameText);
        newPassword = findViewById(R.id.passwordText);
        registerNewUserButton = findViewById(R.id.loginButton1);
    }

    private void setupLoginButton() {
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login_Page.class);
            startActivity(intent);
        });
    }

    private void setupRegisterButton() {
        registerNewUserButton.setOnClickListener(v -> {
            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userUsername = newUsername.getText().toString().trim();
            String userPassword = newPassword.getText().toString().trim();

            if (userName.isEmpty() || userEmail.isEmpty() || userUsername.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.checkUsernameExists(userUsername)) {
                Toast.makeText(MainActivity.this, "Username is already taken", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isInserted = db.insertUser(userName, userEmail, userUsername, userPassword);
            if (isInserted) {
                Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Login_Page.class));
            } else {
                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



