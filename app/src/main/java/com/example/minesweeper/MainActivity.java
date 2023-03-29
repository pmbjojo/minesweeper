package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.minesweeper.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    private ActivityMainBinding binding;

    GridRecyclerViewAdapter adapter;
    GameEngine gameEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Difficulty difficulty = (Difficulty)bundle.getSerializable("difficulty");

        gameEngine = new GameEngine(difficulty);
        RecyclerView recyclerView = findViewById(R.id.grid);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 8);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new GridRecyclerViewAdapter(gameEngine.grid, this);
        adapter = new GridRecyclerViewAdapter(this, cells);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void cellClick(Cell cell) {
        gameEngine.handleCellClick(cell);
        if (gameEngine.gameOver) {
            Intent intent = new Intent(MainActivity.this,EndGame.class);
            intent.putExtra("isGameWon",gameEngine.isGameWon());
            startActivity(intent);
        }
        GridRecyclerViewAdapter.setCells(GameEngine.);
    }
}