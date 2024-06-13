package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class TricepDips_Exercise extends AppCompatActivity {

    private VideoView tricepVid;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tricep_dips_exercise);

        tricepVid = findViewById(R.id.tricep_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tricep_vid);
        tricepVid.setVideoURI(uri);

        Button startBtn = findViewById(R.id.tricep_startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tricepVid.start();
            }
        });
    }
}
