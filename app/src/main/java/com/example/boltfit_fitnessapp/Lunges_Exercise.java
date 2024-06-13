package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Lunges_Exercise extends AppCompatActivity {

    private VideoView lungesVid;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunges_exercise);

        lungesVid = findViewById(R.id.lunges_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lunges_vid);
        lungesVid.setVideoURI(uri);

        Button startBtn = findViewById(R.id.lunges_startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lungesVid.start();
            }
        });
    }
}
