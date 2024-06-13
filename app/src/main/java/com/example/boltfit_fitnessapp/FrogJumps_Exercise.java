package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class FrogJumps_Exercise extends AppCompatActivity {

    VideoView frogVid;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frog_jumps_exercise);

        frogVid = (VideoView) findViewById(R.id.frog_vid);
        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.frogjump_vid);
        frogVid.setVideoURI(uri);

        Button frogBtn = (Button) findViewById(R.id.frog_startBtn);
        frogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frogVid.start();
            }
        });
    }
}
