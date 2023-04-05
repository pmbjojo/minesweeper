package com.example.minesweeper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minesweeper.databinding.ActivityResultBinding;

import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    private ActivityResultBinding binding;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Result result = (Result) bundle.getSerializable("result");
        String difficulty = bundle.getString("difficulty", "NOT FOUND");
        int time = intent.getIntExtra("time", 0);
        if (result.equals(Result.WIN)) {
            binding.result.setText("You Win!");
            mediaPlayer = MediaPlayer.create(this, R.raw.victory);
            mediaPlayer.start();
        } else {
            binding.result.setText("You Lose!");
            mediaPlayer = MediaPlayer.create(this, R.raw.explosion);
            mediaPlayer.start();
        }


        DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        String date = sdf.format(new Date());

        values.put(DatabaseHelper.DATE_COLUMN, date);
        values.put(DatabaseHelper.RESULT_COLUMN, result.equals(Result.WIN));
        values.put(DatabaseHelper.TIME_COLUMN, time);
        values.put(DatabaseHelper.DIFFICULTY_COLUMN, difficulty);
        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();

        binding.menu.setOnClickListener(v -> {
            Intent intentMenu = new Intent(ResultActivity.this, MenuActivity.class);
            startActivity(intentMenu);
            finish();
        });
    }

    @Override
    protected void onStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}