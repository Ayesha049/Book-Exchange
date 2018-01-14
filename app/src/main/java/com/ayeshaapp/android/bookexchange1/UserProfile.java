package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.Book;
import com.ayeshaapp.android.bookexchange1.models.profile;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by l on 11/4/17.
 */

public class UserProfile extends AppCompatActivity {


    private TextView name;
    private TextView email;
    private ImageView photo;
    private TextView phoneNo;
    private profile mprofileObject;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mPhotoStrorageReference;

    private ValueEventListener mpostListener;

    private static final int RC_PHOTO_PICKER =  2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        name= findViewById(R.id.user_profile_name);
        email= findViewById(R.id.user_profile_email);
        photo = findViewById(R.id.user_profile_photo);
        phoneNo = findViewById(R.id.user_profile_phone);


        name.setText(BuySell.finalname);
        email.setText(BuySell.finalemail);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(BuySell.finalUid);
        mFirebaseStorage = FirebaseStorage.getInstance();
        mPhotoStrorageReference = mFirebaseStorage.getReference().child("profile_photos");



        mprofileObject = new profile();


        Button btn = findViewById(R.id.Show_my_booklist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(UserProfile.this, ShowMyBookList.class);
                startActivity(familyIntent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.edit_profile_menu:
                Intent numbersIntent = new Intent(UserProfile.this, EditProfile.class);
                startActivity(numbersIntent);
                break;

            case R.id.reset_password_menu:
                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.sendPasswordResetEmail(BuySell.finalemail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // do something when mail was sent successfully.
                                    Toast.makeText(UserProfile.this,"Successfully sent reset password link to your email",Toast.LENGTH_LONG).show();
                                } else {
                                    // ...
                                }
                            }
                        });

                break;

            default:
                return super.onOptionsItemSelected(item);

        }


        return true;
    }


    @Override
    public void onStart() {
        super.onStart();

        mpostListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                profile obj = dataSnapshot.getValue(profile.class);

                if(obj!=null)
                {
                    mprofileObject=obj;

                }
                else
                {
                    mprofileObject.setEmail(BuySell.finalemail);
                    mprofileObject.setName(BuySell.finalname);
                    mprofileObject.setPhotourl("https://firebasestorage.googleapis.com/v0/b/book-exchange-49.appspot.com/o/profile.jpg?alt=media&token=bed7b9d0-70b6-4a7c-bc05-a17991e402b3");
                    mprofileObject.setPhoneno("01*********");
                }

                name.setText( mprofileObject.getName());
                phoneNo.setText(mprofileObject.getPhoneno());
                Glide.with(photo.getContext())
                        .load(mprofileObject.getPhotourl())
                        .into(photo);



                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabaseReference.addValueEventListener(mpostListener);

        //mDatabaseReference.setValue(mprofileObject);



        // [END post_value_event_listener]


    }

    protected void onResume() {
        super.onResume();
        mDatabaseReference.addValueEventListener(mpostListener);
    }




}
