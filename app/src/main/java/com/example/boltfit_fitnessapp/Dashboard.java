package com.example.boltfit_fitnessapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView menuBtn;
    private ImageButton upperbodyBtn, lowerbodyBtn, cardioBtn, stretchBtn;
    private TextView workoutName, email, name, textView10;
    private String username;
    private DatabaseHelper db;
    private LinearLayout scrollContent;
    private LayoutInflater inflater;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeViews();
        setupNavigationDrawer();
        setupButtonListeners();

        db = new DatabaseHelper(this);
        username = getIntent().getStringExtra("username");

        if (username != null) {
            textView10.setText("Hi " + username + "!");
        }
    }

    private void initializeViews() {
        scrollContent = findViewById(R.id.scroll_content);
        inflater = LayoutInflater.from(this);

        workoutName = findViewById(R.id.workout_name);
        upperbodyBtn = findViewById(R.id.upperbody_contentBtn);
        lowerbodyBtn = findViewById(R.id.lowerbody_contentBtn);
        cardioBtn = findViewById(R.id.cardio_contentBtn);
        stretchBtn = findViewById(R.id.stretch_contentBtn);
        menuBtn = findViewById(R.id.menu_button);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        name = findViewById(R.id.nameText1);
        email = findViewById(R.id.emailText1);
        textView10 = findViewById(R.id.textView10);
    }

    private void setupNavigationDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.nameText1);
        email = headerView.findViewById(R.id.emailText1);
        displayUserDetails();
        menuBtn.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void setupButtonListeners() {
        upperbodyBtn.setOnClickListener(v -> updateWorkoutContent("UPPER BODY WORKOUT", R.layout.layout_upperbody));
        lowerbodyBtn.setOnClickListener(v -> updateWorkoutContent("LOWER BODY WORKOUT", R.layout.layout_lowerbody));
        cardioBtn.setOnClickListener(v -> updateWorkoutContent("CARDIO WORKOUT", R.layout.layout_cardio));
        stretchBtn.setOnClickListener(v -> updateWorkoutContent("STRETCHING WORKOUT", R.layout.layout_stretch));
    }

    private void updateWorkoutContent(String workout, @LayoutRes int layoutResId) {
        workoutName.setText(workout);
        updateScrollViewContent(layoutResId);
    }

    private void displayUserDetails() {
        if (username != null && !username.isEmpty()) {
            String[] userDetails = db.getUserDetailsByUsername(username);

            if (userDetails != null && userDetails.length >= 2 && userDetails[0] != null && userDetails[1] != null) {
                name.setText(userDetails[0]);
                email.setText(userDetails[1]);
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No username provided", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void updateScrollViewContent(@LayoutRes int layoutResId) {
        scrollContent.removeAllViews();
        View newContent = inflater.inflate(layoutResId, scrollContent, false);
        scrollContent.addView(newContent);
    }
}


