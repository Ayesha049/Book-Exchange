package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayeshaapp.android.bookexchange1.models.Book;
import com.bumptech.glide.Glide;

/**
 * Created by l on 11/5/17.
 */

public class Advertise extends AppCompatActivity {
    private Book myBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertise);


        TextView nameTextView = findViewById(R.id.show_book_to_order_bookName);
        TextView authorTextView = findViewById(R.id.show_book_to_order_bookWriter);
        TextView priceTextView = findViewById(R.id.show_book_to_order_bookPrice);
        ImageView bookImage = findViewById(R.id.show_book_to_order_bookPic);


         myBook = (Book)getIntent().getParcelableExtra("Book");

        nameTextView.setText(myBook.getBookname());
        authorTextView.setText(myBook.getAuthorname());
        priceTextView.setText(myBook.getPrice());
        Glide.with(bookImage.getContext())
                .load(myBook.getPhotoUrl())
                .into(bookImage);

        Button orderButton = findViewById(R.id.advertise_order);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(Advertise.this, CompleteOrder.class);
                familyIntent.putExtra("CBook",myBook);

                startActivity(familyIntent);
            }
        });


    }
}
