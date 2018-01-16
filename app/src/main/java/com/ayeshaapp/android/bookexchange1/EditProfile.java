package com.ayeshaapp.android.bookexchange1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ayeshaapp.android.bookexchange1.models.profile;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by l on 1/14/18.
 */

public class EditProfile extends AppCompatActivity {



    private EditText nameEdit;
    private EditText phoneEdit;
    private ImageView photoEdit;
    private ImageView photo;
    private Button save;

    private profile mprofileObject;

    private String phurl;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mPhotoStrorageReference;

    private int photoupload = 0;

    private static final int RC_PHOTO_PICKER =  2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        phoneEdit = findViewById(R.id.edit_profile_phone);
        nameEdit = findViewById(R.id.edit_profile_name);
        photoEdit = findViewById(R.id.edit_photo);

        photo = findViewById(R.id.edit_profile_photo);

        save = findViewById(R.id.edit_profile_save);

        phurl = "";


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(BuySell.finalUid);
        mFirebaseStorage = FirebaseStorage.getInstance();
        mPhotoStrorageReference = mFirebaseStorage.getReference().child("profile_photos");

        mprofileObject = new profile();
        mprofileObject = (profile) getIntent().getParcelableExtra("Profile");
        //Toast.makeText(EditProfile.this,mprofileObject.getEmail(),Toast.LENGTH_LONG).show();

        phoneEdit.setText(mprofileObject.getPhoneno());
        nameEdit.setText(mprofileObject.getName());


        photoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
                //mDatabaseReference.setValue(mprofileObject);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String namee = nameEdit.getText().toString();
                String phonee = phoneEdit.getText().toString();
                if(!namee.equals(""))
                {
                    mDatabaseReference.child("name").setValue(namee);
                    mprofileObject.setName(namee);
                }
                if(photoupload==1)
                {
                    mDatabaseReference.child("photourl").setValue(phurl);
                    mprofileObject.setPhotourl(phurl);
                }

                if(!phonee.equals(""))
                {
                    mDatabaseReference.child("phoneno").setValue(phonee);
                    mprofileObject.setPhoneno(phonee);
                }
                /*if(!mprofileObject.getPhotourl().equals(""))
                {

                }*/


                Intent numbersIntent = new Intent(EditProfile.this, UserProfile.class);
                numbersIntent.putExtra("Profile",mprofileObject);
                startActivity(numbersIntent);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                //Toast.makeText(EditProfile.this,"back clicked",Toast.LENGTH_LONG).show();
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
        //Toast.makeText(EditProfile.this,"back pressed",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("Profile", mprofileObject);
        setResult(Activity.RESULT_OK, intent);
        finish();
        //startActivity(intent);
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
                            phurl = taskSnapshot.getDownloadUrl().toString();
                            photoupload = 1;

                            //mprofileObject.setPhotourl(taskSnapshot.getDownloadUrl().toString());

                            Glide.with(photo.getContext())
                                    .load(taskSnapshot.getDownloadUrl().toString())
                                    .into(photo);
                        }

                    });
        }
    }
}
