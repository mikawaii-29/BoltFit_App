package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;


import androidx.appcompat.app.AppCompatActivity;


public class Pushup_Exercise extends AppCompatActivity {

    VideoView pushupvid;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushup_exercise);

        pushupvid = (VideoView) findViewById(R.id.pushup_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pushup_vid);
        pushupvid.setVideoURI(uri);

        Button puBtn = (Button) findViewById(R.id.pushup_startBtn);
        puBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushupvid.start();
            }
        });



    }
}