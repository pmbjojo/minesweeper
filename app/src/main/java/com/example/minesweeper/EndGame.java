package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.minesweeper.databinding.ActivityEndGameBinding;
import com.example.minesweeper.databinding.ActivityMainBinding;

public class EndGame extends AppCompatActivity {

    public MediaPlayer MediaPlayerExplose;
    public MediaPlayer MediaPlayerVictory;

    private ActivityEndGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        binding = ActivityEndGameBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intentEG = new Intent(EndGame.this, StartGame.class);
        Bundle bundle = new Bundle();
        binding.egHomebutton.setOnClickListener(v -> {
            intentEG.putExtras(bundle);
            startActivity(intentEG);
        });
        Intent intent = getIntent();
        boolean result = intent.getBooleanExtra("isGameWon", false);

        if(result)
        {
            binding.result.setText(R.string.eg_result_win);
            MediaPlayerExplose = MediaPlayer.create(this, R.raw.explosion);
            MediaPlayerExplose.start();
        }
        else
        {
            binding.result.setText(R.string.eg_result_lose);
            MediaPlayerVictory = MediaPlayer.create(this, R.raw.victory);
            MediaPlayerVictory.start();
        }

    }
}