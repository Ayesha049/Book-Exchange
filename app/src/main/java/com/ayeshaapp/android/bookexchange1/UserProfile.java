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


        //mprofileObject = new profile();
        mprofileObject = (profile) getIntent().getParcelableExtra("Profile");

        //Toast.makeText(UserProfile.this,mprofileObject.getName(),Toast.LENGTH_LONG).show();
        name.setText( mprofileObject.getName());
        phoneNo.setText(mprofileObject.getPhoneno());
        Glide.with(photo.getContext())
                .load(mprofileObject.getPhotourl())
                .into(photo);

        Button btn = findViewById(R.id.Show_my_booklist);
        if(!mprofileObject.getUid().equals(BuySell.finalUid))
        {
            invalidateOptionsMenu();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(UserProfile.this, ShowMyBookList.class);
                familyIntent.putExtra("UProfile",mprofileObject);
                //startActivity(familyIntent);
                startActivityForResult(familyIntent,2);
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_profile_menu,menu);
        if(!mprofileObject.getUid().equals(BuySell.finalUid))
        {
            MenuItem item = menu.findItem(R.id.edit_profile_menu);
            item.setVisible(false);
            MenuItem item1 = menu.findItem(R.id.reset_password_menu);
            item1.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.edit_profile_menu:
                mDatabaseReference.setValue(mprofileObject);
                Intent numbersIntent = new Intent(UserProfile.this, EditProfile.class);
                numbersIntent.putExtra("Profile",mprofileObject);
                startActivityForResult(numbersIntent,2);
                break;

            case R.id.reset_password_menu:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(BuySell.finalemail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UserProfile.this,"Successfully sent reset password link to your email",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(UserProfile.this,"Failed to sent reset password",Toast.LENGTH_LONG).show();
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

    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                mprofileObject = (profile) data.getParcelableExtra("Profile");

            }
        }

    }
}
