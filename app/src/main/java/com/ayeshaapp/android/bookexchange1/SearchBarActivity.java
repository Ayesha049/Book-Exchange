package com.ayeshaapp.android.bookexchange1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by l on 1/2/18.
 */


public class SearchBarActivity extends AppCompatActivity implements RecyclerViewClickListener {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> Booknamelist;
    ArrayList<String> Authorlist;
    ArrayList<String> BookPiclist;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
        * Create a array list for each node you want to use
        * */
        Booknamelist = new ArrayList<>();
        Authorlist = new ArrayList<>();
        BookPiclist = new ArrayList<>();

        //

        /*Intent i = new Intent(this, Advertise.class);
        startActivity(i);*/

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());

                } else {
                    /*
                    * Clear the list when editText is empty
                    * */
                    Booknamelist.clear();
                    Authorlist.clear();
                    BookPiclist.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

        //searchAdapter.setClickListener(this);
    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                * Clear the list for every new search
                * */
                Booknamelist.clear();
                Authorlist.clear();
                BookPiclist.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                * Search all users for matching searched string
                * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    for(DataSnapshot mysnapshot : snapshot.getChildren())
                    {
                        String full_name = mysnapshot.child("bookname").getValue(String.class);
                        String user_name = mysnapshot.child("authorname").getValue(String.class);
                        String profile_pic = mysnapshot.child("photoUrl").getValue(String.class);

                        if (full_name.toLowerCase().contains(searchedString.toLowerCase())) {
                            Booknamelist.add(full_name);
                            Authorlist.add(user_name);
                            BookPiclist.add(profile_pic);
                            counter++;
                        } else if (user_name.toLowerCase().contains(searchedString.toLowerCase())) {
                            Booknamelist.add(full_name);
                            Authorlist.add(user_name);
                            BookPiclist.add(profile_pic);
                            counter++;
                        }
                    }

                    /*
                    * Get maximum of 15 searched results only
                    * */
                    if (counter == 100)
                        break;
                }

                searchAdapter = new SearchAdapter(SearchBarActivity.this, Booknamelist, Authorlist, BookPiclist);
                searchAdapter.setClickListener(SearchBarActivity.this);
                recyclerView.setAdapter(searchAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {


    }
}