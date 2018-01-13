package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        if(myBook.getUidd().equals(BuySell.finalUid))
        {
            orderButton.setText("Delete");
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference c1v2= FirebaseDatabase.getInstance().getReference().child("Books");
                    Query applesQuery = c1v2.child(myBook.getUidd()).orderByChild("bookname").equalTo(myBook.getBookname());
                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Intent numbersIntent = new Intent(Advertise.this, ShowBookList.class);
                    startActivity(numbersIntent);
                }
            });
        }
        else
        {
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    String[] TO = {myBook.getEmail()};
                    String[] CC = {""};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Advertise.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }


                    myBook.increment();

                    DatabaseReference c1v2= FirebaseDatabase.getInstance().getReference().child("Books");
                    Query applesQuery = c1v2.child(myBook.getUidd()).orderByChild("bookname").equalTo(myBook.getBookname());
                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().setValue(myBook);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            });
        }



    }
}
