package com.example.android.bookexchange1.data;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.bookexchange1.data.BookContract.PersonEntry;

import com.example.android.bookexchange1.data.BookContract.AdvertisementEntry;

import com.example.android.bookexchange1.data.BookContract.OrderlistEntry;

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

        String SQL_CREATE_PERSON_TABLE =  "CREATE TABLE " + PersonEntry.PERSON_TABLE_NAME + " ("
                + PersonEntry.PERSON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PersonEntry.COLUMN_PERSON_FIRSTNAME + " TEXT , "
                + PersonEntry.COLUMN_PERSON_LASTNAME + " TEXT , "
                + PersonEntry.COLUMN_PERSON_EMAIL + " TEXT , "
                + PersonEntry.COLUMN_PERSON_PHONE + " TEXT , "
                + PersonEntry.COLUMN_PERSON_PASSWORD + " TEXT);";

        db.execSQL(SQL_CREATE_PERSON_TABLE);


        String SQL_CREATE_ADVERTISE_TABLE =  "CREATE TABLE " + AdvertisementEntry.AD_TABLE_NAME + " ("
                + AdvertisementEntry.AD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AdvertisementEntry.COLUMN_AD_BOOK_NAME + " TEXT, "
                + AdvertisementEntry.COLUMN_AD_AUTHOR_NAME + " TEXT, "
                + AdvertisementEntry.COLUMN_AD_IMAGE + " BLOB, "
                + AdvertisementEntry.COLUMN_AD_PRICE + " INTEGER,"
                + AdvertisementEntry.COLUMN_AD_BOOKTAG + " TEXT, "
                + AdvertisementEntry.COLUMN_AD_PERSON_ID + " TEXT);";

        db.execSQL(SQL_CREATE_ADVERTISE_TABLE);

        String SQL_CREATE_ORDER_TABLE =  "CREATE TABLE " + OrderlistEntry.ORDER_TABLE_NAME + " ("
                + OrderlistEntry.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OrderlistEntry.ORDER_PERSON_ID + " TEXT, "
                + OrderlistEntry.ORDER_AD_ID + " TEXT);";

        db.execSQL(SQL_CREATE_ORDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
