package com.example.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by l on 11/5/17.
 */

public class Advertise extends AppCompatActivity {





    public static Book myBook = null;

    public void passBook(Book book) {
        myBook = book;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertise);



        TextView nameTextView = (TextView) findViewById(R.id.show_book_to_order_bookName);
        TextView authorTextView = (TextView) findViewById(R.id.show_book_to_order_bookWriter);
        TextView priceTextView = (TextView) findViewById(R.id.show_book_to_order_bookPrice);
        ImageView bookImageView = (ImageView) findViewById(R.id.show_book_to_order_bookPic);



        if (myBook != null) {
            nameTextView.setText(myBook.getBookName());
            authorTextView.setText("Author : "+myBook.getBookWriter());
            priceTextView.setText(myBook.getPrice());
            bookImageView.setImageResource(myBook.getImageId());
        }






        Button orderButton = (Button) findViewById(R.id.advertise_order);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(Advertise.this, CompleteOrder.class);
                startActivity(familyIntent);
            }
        });






    }
}
