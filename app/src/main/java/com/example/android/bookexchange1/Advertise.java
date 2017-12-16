package com.example.android.bookexchange1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookexchange1.models.Book;

/**
 * Created by l on 11/5/17.
 */

public class Advertise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertise);


        TextView nameTextView = findViewById(R.id.show_book_to_order_bookName);
        TextView authorTextView = findViewById(R.id.show_book_to_order_bookWriter);
        TextView priceTextView = findViewById(R.id.show_book_to_order_bookPrice);


        Book myBook = (Book)getIntent().getParcelableExtra("Book");
       /* Bitmap bmp = BitmapFactory.decodeByteArray(myBook.getImage(),0,myBook.getImage().length);

        bookImageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, bookImageView.getWidth(),
                bookImageView.getHeight(), false));*/
        /*nameTextView.setText(myBook.getBookName());
        authorTextView.setText(myBook.getBookWriter());*/
        priceTextView.setText(myBook.getPrice());
        //bookImageView.setImageResource(myBook.getImageId());

        Button orderButton = findViewById(R.id.advertise_order);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(Advertise.this, CompleteOrder.class);
                startActivity(familyIntent);
            }
        });


    }
}
