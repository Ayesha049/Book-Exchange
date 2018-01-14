package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayeshaapp.android.bookexchange1.models.Book;
import com.ayeshaapp.android.bookexchange1.models.profile;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private ImageView photoEdit;
    private ImageView nameEdit;
    private ImageView phoneEdit;




    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener mChildEventListener;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mPhotoStrorageReference;

    private static final int RC_PHOTO_PICKER =  2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        name= findViewById(R.id.user_profile_name);
        email= findViewById(R.id.user_profile_email);
        photo = findViewById(R.id.user_profile_photo);
        phoneNo = findViewById(R.id.user_profile_phone);

        phoneEdit = findViewById(R.id.edit_phoneno);
        nameEdit = findViewById(R.id.edit_name);
        photoEdit = findViewById(R.id.edit_photo);

        name.setText(BuySell.finalname);
        email.setText(BuySell.finalemail);



        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(BuySell.finalUid);
        mFirebaseStorage = FirebaseStorage.getInstance();
        mPhotoStrorageReference = mFirebaseStorage.getReference().child("profile_photos");



        mprofileObject = new profile();
        mprofileObject.setEmail(BuySell.finalemail);
        mprofileObject.setName(BuySell.finalname);



        if(mprofileObject!=null)
        {
            name.setText( mprofileObject.getName());
            phoneNo.setText(mprofileObject.getPhoneno());
            email.setText(mprofileObject.getEmail());
            Glide.with(photo.getContext())
                    .load(mprofileObject.getPhotourl())
                    .into(photo);
        }



        photoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

            }
        });



        Button btn = findViewById(R.id.Show_my_booklist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference.setValue(mprofileObject);
                Intent familyIntent = new Intent(UserProfile.this, ShowMyBookList.class);
                startActivity(familyIntent);
            }
        });







    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_PHOTO_PICKER && resultCode==RESULT_OK)
        {
            Uri SelectedImageUri = data.getData();
            StorageReference photoRef =
                    mPhotoStrorageReference.child(SelectedImageUri.getLastPathSegment());

            photoRef.putFile(SelectedImageUri).addOnSuccessListener(
                    this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            mprofileObject.setPhotourl(taskSnapshot.getDownloadUrl().toString());
                            Glide.with(photo.getContext())
                                    .load(taskSnapshot.getDownloadUrl().toString())
                                    .into(photo);
                        }

                    });
        }
    }
}
