package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.minesweeper.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = new Intent(MenuActivity.this, GameActivity.class);
        Bundle bundle = new Bundle();
        binding.easy.setOnClickListener(v -> {
            bundle.putSerializable("difficulty", Difficulty.EASY);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
        binding.medium.setOnClickListener(v -> {
            bundle.putSerializable("difficulty", Difficulty.MEDIUM);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
        binding.hard.setOnClickListener(v -> {
            bundle.putSerializable("difficulty", Difficulty.HARD);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        List<String> itemList = new ArrayList<String>();
        DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.ID_COLUMN, DatabaseHelper.DATE_COLUMN, DatabaseHelper.RESULT_COLUMN, DatabaseHelper.TIME_COLUMN};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper.DATE_COLUMN + " DESC");
        while (cursor.moveToNext()) {
            int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int id = idColumnIndex == -1 ? 0 : cursor.getInt(idColumnIndex);

            int dateColumnIndex = cursor.getColumnIndex(DatabaseHelper.DATE_COLUMN);
            String date = dateColumnIndex == -1 ? "" : cursor.getString(dateColumnIndex);

            int resultColumnIndex = cursor.getColumnIndex(DatabaseHelper.RESULT_COLUMN);
            int result = resultColumnIndex == -1 ? 0 : cursor.getInt(resultColumnIndex);


            int scoreColumnIndex = cursor.getColumnIndex(DatabaseHelper.TIME_COLUMN);
            int score = scoreColumnIndex == -1 ? 0 : cursor.getInt(scoreColumnIndex);
            if (result == 1) {
                itemList.add(date + "_" + score);
            }
        }
        cursor.close();
        db.close();

        RecyclerView recyclerView = findViewById(R.id.rv_ranking);
        RankingAdapter adapter = new RankingAdapter(itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}