package com.example.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by l on 11/3/17.
 */

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);



        Button signnUp = (Button) findViewById(R.id.logggin);

        signnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(LogIn.this, BuySell.class);
                startActivity(numbersIntent);
            }
        });



    }
}
