package com.example.boltfit_fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    ImageView menuBtn;
    ImageButton upperbodyBtn, lowerbodyBtn, cardioBtn, stretchBtn;

    private LinearLayout scrollContent;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        scrollContent = findViewById(R.id.scroll_content);
        inflater = LayoutInflater.from(this);

        upperbodyBtn = findViewById(R.id.upperbody_contentBtn);
        lowerbodyBtn = findViewById(R.id.lowerbody_contentBtn);
        cardioBtn = findViewById(R.id.cardio_contentBtn);
        stretchBtn = findViewById(R.id.stretch_contentBtn);
        menuBtn = findViewById((R.id.menu_button));
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        upperbodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                updateScrollViewContent(R.layout.layout_upperbody);
            }
        });

        lowerbodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateScrollViewContent(R.layout.layout_lowerbody);
            }
        });

        cardioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateScrollViewContent(R.layout.layout_cardio);
            }
        });

        stretchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateScrollViewContent(R.layout.layout_stretch);
            }
        });

        {
            {

        }

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
    private void updateScrollViewContent(int layoutResId) {
        scrollContent.removeAllViews();
        View newContent = inflater.inflate(layoutResId, scrollContent, false);
        scrollContent.addView(newContent);
    }
}