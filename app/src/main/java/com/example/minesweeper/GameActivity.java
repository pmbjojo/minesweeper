package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.minesweeper.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity implements OnCellClickListener {
    private ActivityGameBinding binding;

    GridAdapter adapter;
    GameEngine gameEngine;

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
        adapter = new GridAdapter(gameEngine.getGrid().getCellList(), this);
        binding.grid.setAdapter(adapter);

        binding.mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameEngine.switchMode();
                Toast.makeText(GameActivity.this, "Mode : " + gameEngine.getMode().name(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.bMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onCellClick(Cell cell) {
        //Toast.makeText(this, "Cell clicked", Toast.LENGTH_SHORT).show();
        gameEngine.handleMode(cell);
        if (gameEngine.getStatus().equals(Status.OVER)) {
            //Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("result", gameEngine.getResult());
            intent.putExtra("time", Integer.valueOf(binding.time.getText().toString()));
            startActivity(intent);
            finish();
        }
        binding.mines.setText(String.format("%03d", gameEngine.getFlags()));
        adapter.setCells(gameEngine.getGrid().getCellList().indexOf(cell));
    }

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