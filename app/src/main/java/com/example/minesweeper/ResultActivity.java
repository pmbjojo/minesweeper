package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;

import com.example.minesweeper.databinding.ActivityResultBinding;

import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    private ActivityResultBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Result result = (Result) bundle.getSerializable("result");
        int time = intent.getIntExtra("time", 0);
        if(result.equals(Result.WIN)) {
            binding.result.setText("You Win!");
        } else {
            binding.result.setText("You Lose!");
        }


        DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        String date = sdf.format(new Date());

        values.put(DatabaseHelper.DATE_COLUMN, date);
        values.put(DatabaseHelper.RESULT_COLUMN, result.equals(Result.WIN));
        values.put(DatabaseHelper.TIME_COLUMN, time);
        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();

        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}