package com.example.android.bookexchange1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by l on 11/17/17.
 */

public class BookDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "bookExchange.db";
    private static final int DATABASE_VERSION = 1;


    public BookDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
