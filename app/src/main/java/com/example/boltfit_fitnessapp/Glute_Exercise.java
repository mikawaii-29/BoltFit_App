package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Glute_Exercise extends AppCompatActivity {

    private VideoView gluteVid;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glute_exercise);

        gluteVid = findViewById(R.id.glute_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.glute_vid);
        gluteVid.setVideoURI(uri);

        Button startBtn = findViewById(R.id.glute_startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gluteVid.start();
            }
        });
    }
}
