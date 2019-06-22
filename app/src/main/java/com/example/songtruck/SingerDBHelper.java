package com.example.songtruck;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.songtruck.ItemContract.SingerEntry;

public class SingerDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BookedSinger.db";
    public static final int DATABASE_VERSION = 1;


    public SingerDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_SINGERLIST_TABLE = "CREATE TABLE " +
                SingerEntry.TABLE_NAME + " (" +
                SingerEntry.COLUMN_NAME + " TEXT PRIMARY KEY, " +
                SingerEntry.COLUMN_ICON + " INTEGER NOT NULL, " +
                SingerEntry.COLUMN_RATING + " INTEGER, " +
                SingerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_SINGERLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
