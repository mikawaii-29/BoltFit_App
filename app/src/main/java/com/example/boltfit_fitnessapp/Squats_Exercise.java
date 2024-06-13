package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Squats_Exercise extends AppCompatActivity {

    private VideoView squatsVid;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squats_exercise);

        squatsVid = findViewById(R.id.squats_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.squats_vid);
        squatsVid.setVideoURI(uri);

        Button startBtn = findViewById(R.id.squats_startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                squatsVid.start();
            }
        });
    }
}
