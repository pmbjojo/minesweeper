package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minesweeper.databinding.ActivityMainBinding;
import com.example.minesweeper.databinding.ActivityStartGameBinding;

public class StartGame extends AppCompatActivity {
    private ActivityStartGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.sgButtonEasy.setOnClickListener(v -> {
            Intent intent = new Intent(StartGame.this,MainActivity.class);
            intent.putExtra("difficulty",Difficulty.EASY);
            startActivity(intent);
        });
        binding.sgButtonMedium.setOnClickListener(v -> {
            Intent intent = new Intent(StartGame.this,MainActivity.class);
            intent.putExtra("difficulty",Difficulty.MEDIUM);
            startActivity(intent);
        });
        binding.sgButtonHard.setOnClickListener(v -> {
            Intent intent = new Intent(StartGame.this,MainActivity.class);
            intent.putExtra("difficulty",Difficulty.HARD);
            startActivity(intent);
        });
    }
}