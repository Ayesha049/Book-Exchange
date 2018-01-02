package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by l on 11/5/17.
 */

public class ShowMyBookList extends AppCompatActivity {

    static ArrayList<Book> books = null;



    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private DatabaseReference mRef;
    private ChildEventListener mLis;
    private BookAdapter madapter;
    private String muid;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_booklist);

        FloatingActionButton fabb = findViewById(R.id.myfab);


        books = new ArrayList<Book>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child("Books").child(BuySell.finalUid);
        madapter = new BookAdapter(this, books);

        ListView listView = findViewById(R.id.my_booklist);

        listView.setAdapter(madapter);


        onSignedInInitialize();

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = books.get(position);;

                Intent familyIntent = new Intent(ShowMyBookList.this, Advertise.class);
                familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);
            }
        });*/

        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(ShowMyBookList.this, SellingForm.class);
                startActivity(numbersIntent);
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