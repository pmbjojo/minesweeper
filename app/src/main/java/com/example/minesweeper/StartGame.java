package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minesweeper.databinding.ActivityMainBinding;
import com.example.minesweeper.databinding.ActivityStartGameBinding;

import java.io.Serializable;

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
        Intent intent = new Intent(StartGame.this,MainActivity.class);
        Bundle bundle = new Bundle();
        binding.sgButtonEasy.setOnClickListener(v -> {
            bundle.putSerializable("difficulty", Difficulty.EASY);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        binding.sgButtonMedium.setOnClickListener(v -> {
            bundle.putSerializable("difficulty", Difficulty.MEDIUM);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        binding.sgButtonHard.setOnClickListener(v -> {
            bundle.putSerializable("difficulty", Difficulty.HARD);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}