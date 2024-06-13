package com.example.boltfit_fitnessapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ArmCircles_Exercise extends AppCompatActivity {

    private VideoView armCircleVid;
    private Button startStopBtn;
    private TextView timerText;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arm_circles_exercise);

        armCircleVid = findViewById(R.id.armcircle_vid);
        startStopBtn = findViewById(R.id.armcircle_startBtn);
        timerText = findViewById(R.id.timer);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.armcircle_vid);
        armCircleVid.setVideoURI(uri);

        startStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer() {
        armCircleVid.start();
        startStopBtn.setText("STOP");
        isTimerRunning = true;

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsRemaining = (int) (millisUntilFinished / 1000);
                timerText.setText(String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60));
            }

            @Override
            public void onFinish() {
                timerText.setText("00:00");
                stopTimer();
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        armCircleVid.pause();
        startStopBtn.setText(R.string.start);
        isTimerRunning = false;
    }
}
