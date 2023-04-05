package com.example.minesweeper;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class Timer extends Service {
    public static final String MINESWEEPER = "com.example.minesweeper";
    public static final long MILLISINFUTURE = 999999;
    public static final long COUNTDOWNINTERVAL = 1000;
    CountDownTimer countDownTimer;
    Intent intent = new Intent(MINESWEEPER);
    long countDown;

    public Timer() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Game started", Toast.LENGTH_SHORT).show();
        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNTDOWNINTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDown = millisUntilFinished / COUNTDOWNINTERVAL;
                intent.putExtra("countdown", countDown);
                sendBroadcast(intent);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Time finished", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        countDownTimer.cancel();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}