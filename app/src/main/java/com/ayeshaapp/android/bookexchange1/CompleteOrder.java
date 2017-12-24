package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;

/**
 * Created by l on 11/5/17.
 */

public class CompleteOrder extends AppCompatActivity {

    private TextView mprice;
    private TextView mtotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_order);

        mprice = findViewById(R.id.pricesummary);

        mtotal = findViewById(R.id.totalprice);
        Book Bobj = (Book)getIntent().getParcelableExtra("CBook");
        String prc = "Price : " + Bobj.getPrice() + "/=";
        mprice.setText(prc);
        Integer tot = Integer.parseInt(Bobj.getPrice())+50;
        prc = "Total : " + tot.toString() + "/=";
        mtotal.setText(prc);




        Button continueBuying = findViewById(R.id.continue_buying);
        continueBuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(CompleteOrder.this, ShowBookList.class);
                startActivity(familyIntent);
            }
        });


        Button chkout = findViewById(R.id.complete_your_order_checkout);
        chkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CompleteOrder.this, "Your Order Has Been Completed", Toast.LENGTH_LONG).show();

                Intent familyIntent = new Intent(CompleteOrder.this, BuySell.class);
                startActivity(familyIntent);
            }
        });


    }
}
