package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.minesweeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCellClick(Cell cell) {
        Toast.makeText(this, "Cell clicked", Toast.LENGTH_SHORT).show();
    }
}