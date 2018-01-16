package com.ayeshaapp.android.bookexchange1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;

import com.ayeshaapp.android.bookexchange1.models.profile;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by l on 11/5/17.
 */

public class ShowBookList extends AppCompatActivity {

    static ArrayList<Book> books = null;



    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private DatabaseReference mRef;
    private ChildEventListener mLis;
    private BookAdapter madapter;
    private profile mprofileObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        FloatingActionButton fabb = findViewById(R.id.fab);
        EditText add_btn = findViewById(R.id.add_button);


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
                Book Books = books.get(position);

                Intent familyIntent = new Intent(ShowBookList.this, Advertise.class);
                familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(ShowBookList.this, SearchBarActivity.class);
                startActivity(numbersIntent);
            }
        });



        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(ShowBookList.this, SellingForm.class);
                startActivity(numbersIntent);
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent returnIntent = new Intent();
                returnIntent.putExtra("flag",5);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            case R.id.menu_profile:
                Intent familyIntent = new Intent(ShowBookList.this, UserProfile.class);
                familyIntent.putExtra("Profile",mprofileObject);
                startActivity(familyIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseDatabase.getReference().child("Users").child(BuySell.finalUid).addValueEventListener(new ValueEventListener() {
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



    private void onSignedInInitialize() {
        if(mChildEventListener==null)
        {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    //String ss = dataSnapshot.child(key).toString();
                    mRef = mFirebaseDatabase.getReference().child("Books").child(key);
                    //if(mLis == null)
                    //{
                        mLis = new ChildEventListener() {
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
                        mRef.addChildEventListener(mLis);
                    //}
                    //Toast.makeText(ShowBookList.this,s,Toast.LENGTH_LONG).show();
                    //Book fm = dataSnapshot.child(key).getValue(Book.class);

                    //madapter.add(fm);
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