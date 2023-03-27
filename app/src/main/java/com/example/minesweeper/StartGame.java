package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.minesweeper.databinding.ActivityMainBinding;
import com.example.minesweeper.databinding.ActivityStartGameBinding;

public class StartGame extends AppCompatActivity {
    private ActivityStartGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        binding = ActivityStartGameBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.sgButtonEasy.setOnClickListener(v -> {
            Intent intent = new Intent(StartGame.this,MainActivity.class);
            startActivity(intent);
        });
    }
}