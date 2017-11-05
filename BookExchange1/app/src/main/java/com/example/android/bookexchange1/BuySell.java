package com.example.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by l on 11/3/17.
 */

public class BuySell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_sell);


        ImageView logout = (ImageView) findViewById(R.id.log_out);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, MainActivity.class);
                startActivity(numbersIntent);
            }
        });


        ImageView pro = (ImageView) findViewById(R.id.profile);

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, UserProfile.class);
                startActivity(numbersIntent);
            }
        });



        Button buy = (Button) findViewById(R.id.buy_books);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, ShowBookList.class);
                startActivity(numbersIntent);
            }
        });



        Button sell = (Button) findViewById(R.id.sell_books);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, SellingForm.class);
                startActivity(numbersIntent);
            }
        });



    }
}
