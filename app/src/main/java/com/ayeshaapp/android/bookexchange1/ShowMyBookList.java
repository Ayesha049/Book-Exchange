package com.ayeshaapp.android.bookexchange1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;

import com.ayeshaapp.android.bookexchange1.models.profile;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    private MyBookAdapter madapter;
    private String muid;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;
    private profile mprofileObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_booklist);

        FloatingActionButton fabb = findViewById(R.id.myfab);


        books = new ArrayList<Book>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mprofileObject = (profile) getIntent().getParcelableExtra("UProfile");

        mDatabaseReference = mFirebaseDatabase.getReference().child("Books").child(mprofileObject.getUid());
        madapter = new MyBookAdapter(this, books);

        ListView listView = findViewById(R.id.my_booklist);

        listView.setAdapter(madapter);

        if(mprofileObject.getUid().equals(BuySell.finalUid))
        {
            registerForContextMenu(listView);
        }
        else
        {
            unregisterForContextMenu(listView);
        }

        onSignedInInitialize();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = books.get(position);
                Intent familyIntent = new Intent(ShowMyBookList.this, Advertise.class);
                familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);

            }
        });



        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(ShowMyBookList.this, SellingForm.class);
                startActivity(numbersIntent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deletion_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Integer mposition = info.position;
        //Log.v("showboklist........", mposition.toString());
        int id = item.getItemId();
        switch (id){
            case R.id.remove_notification:
                final Book myBook = books.get(mposition);
                myBook.setCount(0);
                madapter.notifyDataSetChanged();
                DatabaseReference c1v2= FirebaseDatabase.getInstance().getReference().child("Books");
                Query applesQuery1 = c1v2.child(myBook.getUidd()).orderByChild("bookname").equalTo(myBook.getBookname());
                applesQuery1.addListenerForSingleValueEvent(new ValueEventListener() {
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

                break;
            case R.id.delete_menu:

                Book Books = books.get(mposition);
                books.remove(Books);
                madapter.notifyDataSetChanged();
                Query applesQuery = mDatabaseReference.orderByChild("bookname").equalTo(Books.getBookname());

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
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("Profile", mprofileObject);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(ShowMyBookList.this,"back pressed",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("Profile", mprofileObject);
        setResult(Activity.RESULT_OK, intent);
        finish();
        //startActivity(intent);
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