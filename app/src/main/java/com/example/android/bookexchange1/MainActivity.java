package com.example.android.bookexchange1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bookexchange1.data.BookContract;
import com.example.android.bookexchange1.data.BookContract.PersonEntry;
import com.example.android.bookexchange1.data.BookDbHelper;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Button signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(numbersIntent);
            }
        });


        Button login = findViewById(R.id.log_in);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, LogIn.class);
                startActivity(numbersIntent);
            }
        });


    }
}
