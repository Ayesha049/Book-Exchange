package com.example.android.bookexchange1.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.bookexchange1.data.BookContract.PersonEntry;

import java.security.Provider;

/**
 * Created by l on 11/17/17.
 */

public class BookProvider extends ContentProvider {

    public static final String LOG_TAG = BookProvider.class.getSimpleName();

    private static final int PERSONS = 100;
    private static final int PERSON_ID = 101;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY, BookContract.PATH_PERSON, PERSONS);
        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY, BookContract.PATH_PERSON + "/#", PERSON_ID);
    }

    private BookDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new BookDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder)
    {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSONS:
                cursor = database.query(PersonEntry.PERSON_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PERSON_ID:
                selection = PersonEntry.PERSON_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(PersonEntry.PERSON_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PERSONS:
                return insertPerson(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertPerson(Uri uri, ContentValues values) {
        /*String firstName = values.getAsString(PersonEntry.COLUMN_PERSON_FIRSTNAME);
        if (firstName == null) {
            throw new IllegalArgumentException("Person requires a First Name");
        }

        String lastName = values.getAsString(PersonEntry.COLUMN_PERSON_LASTNAME);
        if (lastName == null) {
            throw new IllegalArgumentException("Person requires a Last Name");
        }

        String emailId = values.getAsString(PersonEntry.COLUMN_PERSON_EMAIL);
        if (emailId == null) {
            throw new IllegalArgumentException("Person requires a Email Adress");
        }

        String phoneNo = values.getAsString(PersonEntry.COLUMN_PERSON_PHONE);
        if (phoneNo == null) {
            throw new IllegalArgumentException("Person requires a Phone Number");
        }

        String password = values.getAsString(PersonEntry.COLUMN_PERSON_PASSWORD);
        if (password == null) {
            throw new IllegalArgumentException("Person requires a password");
        }*/



        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(PersonEntry.PERSON_TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
