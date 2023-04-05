package com.example.minesweeper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.minesweeper.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity implements OnCellClickListener {
    GridAdapter adapter;
    GameEngine gameEngine;
    boolean firstClick = false;
    private ActivityGameBinding binding;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long countDown = intent.getLongExtra("countdown", 999);
            binding.time.setText(String.format("%03d", countDown));
            if (countDown == 0) {
                gameEngine.setLose();
                gameEngine.setOver();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Difficulty difficulty = (Difficulty) bundle.getSerializable("difficulty");

        Intent serviceIntent = new Intent(this, Timer.class);
        gameEngine = new GameEngine(this, serviceIntent, difficulty);
        binding.mines.setText(String.format("%03d", gameEngine.getFlags()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, gameEngine.getGrid().getRows());
        binding.grid.setLayoutManager(gridLayoutManager);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int rows = gameEngine.getGrid().getRows();
        int columns = gameEngine.getGrid().getColumns();
        int cellWidth = width / rows - 4;
        int cellHeight = height / columns - 4;
        int cellSize = cellWidth < cellHeight ? cellWidth : cellHeight;

        adapter = new GridAdapter(gameEngine.getGrid().getCellList(), this, cellSize);
        binding.grid.setAdapter(adapter);

        binding.mode.setOnClickListener(v -> {
            gameEngine.switchMode();
            Toast.makeText(GameActivity.this, "Mode : " + gameEngine.getMode().name(), Toast.LENGTH_SHORT).show();
        });
        binding.bMenu.setOnClickListener(v -> {
            Intent intentMenu = new Intent(GameActivity.this, MenuActivity.class);
            startActivity(intentMenu);
            finish();
        });
    }

    @Override
    public void onCellClick(Cell cell) {
        if(!firstClick){
            gameEngine.getGrid().initMines(cell);
            firstClick = true;
        }
        //Toast.makeText(this, "Cell clicked", Toast.LENGTH_SHORT).show();
        gameEngine.handleMode(cell);
        if (gameEngine.getStatus().equals(Status.OVER)) {
            //Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("result", gameEngine.getResult());
            intent.putExtra("time", Integer.valueOf(binding.time.getText().toString()));
            intent.putExtra("difficulty", gameEngine.getGrid().getDifficulty().name());
            startActivity(intent);
            finish();
        }
        binding.mines.setText(String.format("%03d", gameEngine.getFlags()));
        adapter.setCells(gameEngine.getGrid().getCellList().indexOf(cell));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(Timer.MINESWEEPER));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {

        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, Timer.class));
        super.onDestroy();
    }
}