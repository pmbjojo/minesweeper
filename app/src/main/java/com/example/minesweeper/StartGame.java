package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.minesweeper.databinding.ActivityMainBinding;

public class StartGame extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }
}