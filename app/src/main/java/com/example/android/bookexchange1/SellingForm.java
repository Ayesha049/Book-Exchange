package com.example.android.bookexchange1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.bookexchange1.data.BookContract;
import com.example.android.bookexchange1.data.BookDbHelper;
import com.example.android.bookexchange1.models.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by l on 11/5/17.
 */

public class SellingForm extends AppCompatActivity {

    private EditText mbookName;
    private EditText mbookAuthor;
    private ImageView mbookimage;
    private EditText mbookprice;
    private EditText mbookTag;
    private String mpersonId;

    final int REQUEST_CODE_GALLERY = 999;



    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    //private BookDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_for_selling);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child("Books");

        Button continueBuying = findViewById(R.id.form_for_selling_add_button);
        mbookName = findViewById(R.id.form_for_selling_book_name);
        mbookAuthor = findViewById(R.id.form_for_selling_author);

        mbookprice = findViewById(R.id.form_for_selling_price);
        mbookTag = findViewById(R.id.form_for_selling_book_type);


        continueBuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insertBook();

                Book bb = new Book(mbookName.getText().toString()
                        ,mbookAuthor.getText().toString(),
                        mbookprice.getText().toString(),
                        mbookTag.getText().toString());
                mDatabaseReference.push().setValue(bb);


                Intent familyIntent = new Intent(SellingForm.this, ShowBookList.class);
                startActivity(familyIntent);

            }
        });


    }

}
