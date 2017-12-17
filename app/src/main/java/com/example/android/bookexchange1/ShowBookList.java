package com.example.android.bookexchange1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.bookexchange1.data.BookContract;
import com.example.android.bookexchange1.models.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by l on 11/5/17.
 */

public class ShowBookList extends AppCompatActivity {

    static ArrayList<Book> books = null;



    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private BookAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        books = new ArrayList<Book>();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Books");



        madapter = new BookAdapter(this, books);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(madapter);


        onSignedInInitialize();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = books.get(position);;

                Intent familyIntent = new Intent(ShowBookList.this, Advertise.class);
                familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);
            }
        });
    }

    private void onSignedInInitialize() {
        if(mChildEventListener==null)
        {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Book fm = dataSnapshot.getValue(Book.class);
                    madapter.add(fm);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }


}