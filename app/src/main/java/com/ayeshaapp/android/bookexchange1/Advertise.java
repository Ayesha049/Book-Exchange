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
import com.ayeshaapp.android.bookexchange1.models.profile;
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


    private profile mprofileObject;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertise);


        TextView nameTextView = findViewById(R.id.show_book_to_order_bookName);
        TextView authorTextView = findViewById(R.id.show_book_to_order_bookWriter);
        TextView priceTextView = findViewById(R.id.show_book_to_order_bookPrice);
        ImageView bookImage = findViewById(R.id.show_book_to_order_bookPic);

        Button editAd = findViewById(R.id.edit_adverise);
        //TextView orderGuide = findViewById(R.id.order_guide);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users");
        mprofileObject = new profile();


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
            //orderGuide.setVisibility(View.INVISIBLE);
            editAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent numbersIntent = new Intent(Advertise.this, EditMyBook.class);
                    numbersIntent.putExtra("ABook",myBook);
                    startActivityForResult(numbersIntent,2);

                }
            });
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
            //editAd.setVisibility(View.INVISIBLE);
            editAd.setText(" Owners Profile ");

            //Toast.makeText(Advertise.this,mprofileObject.getName(),Toast.LENGTH_LONG).show();
            editAd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent numbersIntent = new Intent(Advertise.this, UserProfile.class);
                    numbersIntent.putExtra("Profile",mprofileObject);
                    startActivity(numbersIntent);

                }
            });
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String[] TO = {myBook.getEmail()};
                    String[] CC = {""};
                    String MyEmail = "Hello,\nI want to buy your book : " + myBook.getBookname() + "\nAuthor : "+ myBook.getAuthorname()+ "\n \nThanks\n";
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Exchange Order");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, MyEmail);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                myBook = (Book) data.getParcelableExtra("Book");

            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference.child(myBook.getUidd()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profile obj = dataSnapshot.getValue(profile.class);
                //Toast.makeText(Advertise.this,"in Datasnapshot",Toast.LENGTH_LONG).show();
                if(obj!=null)
                {
                    mprofileObject=obj;
                }
                else
                {
                    mprofileObject.setName("Unknown");
                    mprofileObject.setPhotourl("https://firebasestorage.googleapis.com/v0/b/book-exchange-49.appspot.com/o/profile.jpg?alt=media&token=bed7b9d0-70b6-4a7c-bc05-a17991e402b3");
                    mprofileObject.setPhoneno("01*********");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
