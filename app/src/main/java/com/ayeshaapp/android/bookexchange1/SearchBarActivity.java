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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;
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


public class SearchBarActivity extends AppCompatActivity {
    private EditText search_edit_text;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private static ArrayList<Book> sbooks = null;
    private BookAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);



        ListView slistView = findViewById(R.id.list);
        sbooks = new ArrayList<Book>();

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        searchAdapter = new BookAdapter(this, sbooks);
        slistView.setAdapter(searchAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        slistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = sbooks.get(position);

                Intent familyIntent = new Intent(SearchBarActivity.this, Advertise.class);
                familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);
            }
        });


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
                    setttt(s.toString());

                } else {
                    sbooks.clear();
                    searchAdapter.notifyDataSetChanged();
                }
            }
        });


    }

    private void setttt(final String searchedString) {
        databaseReference.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                sbooks.clear();
                searchAdapter.notifyDataSetChanged();

                int counter = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    for(DataSnapshot mysnapshot : snapshot.getChildren())
                    {
                        Book obj =  mysnapshot.getValue(Book.class);
                        String full_name = mysnapshot.child("bookname").getValue(String.class);
                        String user_name = mysnapshot.child("authorname").getValue(String.class);
                        String type = mysnapshot.child("booktype").getValue(String.class);

                        if (full_name.toLowerCase().contains(searchedString.toLowerCase())) {
                            sbooks.add(obj);
                            searchAdapter.notifyDataSetChanged();
                            counter++;
                        } else if (user_name.toLowerCase().contains(searchedString.toLowerCase())) {
                            sbooks.add(obj);
                            searchAdapter.notifyDataSetChanged();
                            counter++;
                        }
                        else if (type.toLowerCase().contains(searchedString.toLowerCase())) {
                            sbooks.add(obj);
                            searchAdapter.notifyDataSetChanged();
                            counter++;
                        }
                    }

                    if (counter == 100)
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}