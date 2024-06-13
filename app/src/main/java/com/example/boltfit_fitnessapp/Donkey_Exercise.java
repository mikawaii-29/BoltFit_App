package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Donkey_Exercise extends AppCompatActivity {

    private VideoView donkeyVid;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donkey_exercise);

        donkeyVid = findViewById(R.id.donkey_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.donkey_vid);
        donkeyVid.setVideoURI(uri);

        Button startBtn = findViewById(R.id.donkey_startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donkeyVid.start();
            }
        });
    }
}
