package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.minesweeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Grid grid = new Grid(5, 5, 5);
    }

    @Override
    public void onCellClick(Cell cell) {
        Toast.makeText(this, "Cell clicked", Toast.LENGTH_SHORT).show();
    }
}