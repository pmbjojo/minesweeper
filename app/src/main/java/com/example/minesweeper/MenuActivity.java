package com.example.minesweeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(MenuActivity.this, GameActivity.class);
        int rbId = sharedPreferences.getInt("rb_id", R.id.rb_easy);

        binding.radioGroupId.check(rbId);
        binding.start.setOnClickListener(v -> {
            switch (binding.radioGroupId.getCheckedRadioButtonId()) {
                case R.id.rb_easy:
                    bundle.putSerializable("difficulty", Difficulty.EASY);
                    editor.putInt("rb_id", R.id.rb_easy);
                    break;
                case R.id.rb_medium:
                    bundle.putSerializable("difficulty", Difficulty.MEDIUM);

                    editor.putInt("rb_id", R.id.rb_medium);
                    break;
                case R.id.rb_hard:
                    bundle.putSerializable("difficulty", Difficulty.HARD);
                    editor.putInt("rb_id", R.id.rb_hard);
                    break;
            }
            editor.apply();
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        List<String> itemList = new ArrayList<String>();
        DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
        //dbHelper.onCreate(dbHelper.getWritableDatabase());
        //dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.ID_COLUMN, DatabaseHelper.DATE_COLUMN, DatabaseHelper.RESULT_COLUMN, DatabaseHelper.TIME_COLUMN, DatabaseHelper.DIFFICULTY_COLUMN};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper.DATE_COLUMN + " DESC");
        while (cursor.moveToNext()) {
            int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.ID_COLUMN);
            int dateColumnIndex = cursor.getColumnIndex(DatabaseHelper.DATE_COLUMN);
            int resultColumnIndex = cursor.getColumnIndex(DatabaseHelper.RESULT_COLUMN);
            int difficultyColumnIndex = cursor.getColumnIndex(DatabaseHelper.DIFFICULTY_COLUMN);
            int scoreColumnIndex = cursor.getColumnIndex(DatabaseHelper.TIME_COLUMN);

            int id = idColumnIndex == -1 ? 0 : cursor.getInt(idColumnIndex);
            String date = dateColumnIndex == -1 ? "" : cursor.getString(dateColumnIndex);
            int result = resultColumnIndex == -1 ? 0 : cursor.getInt(resultColumnIndex);
            String difficulty = difficultyColumnIndex == -1 ? "tamerlp" : cursor.getString(difficultyColumnIndex);
            int score = scoreColumnIndex == -1 ? 0 : cursor.getInt(scoreColumnIndex);
            //Log.d("DB", "id: " + id + ", date: " + date + ", result: " + result + ", difficulty: " + difficulty + ", score: " + score);
            if (result == 1) {
                itemList.add(date + "_" + difficulty + "_" + score);
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