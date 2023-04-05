package com.example.minesweeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "minesweeper.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "ranking";
    public static final String ID_COLUMN = "id";
    public static final String DATE_COLUMN = "date";
    public static final String RESULT_COLUMN = "result";
    public static final String TIME_COLUMN = "score";
    public static final String DIFFICULTY_COLUMN = "difficulty";

    private static final String SQL_CREATION_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_COLUMN + " DATE NOT NULL, " + TIME_COLUMN + " INT NOT NULL, " + DIFFICULTY_COLUMN + " TEXT NOT NULL, " + RESULT_COLUMN + " BOOLEAN NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
