package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by l on 11/3/17.
 */

public class BuySell extends AppCompatActivity {


    public static String finalUid = "mee";
    public static String finalemail = "mee";
    public static String finalname = "mee";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;
    public static final int RC_SIGN_IN=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_sell);

        mFirebaseAuth = FirebaseAuth.getInstance();


        ImageView logout = findViewById(R.id.log_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(BuySell.this);
            }
        });




        ImageView pro = findViewById(R.id.profile);

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, UserProfile.class);
                startActivity(numbersIntent);
            }
        });


        Button buy = findViewById(R.id.buy_books);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, ShowBookList.class);
                startActivity(numbersIntent);
            }
        });


        Button sell = findViewById(R.id.sell_books);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(BuySell.this, SellingForm.class);
                startActivity(numbersIntent);
            }
        });


        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());


                if (user != null) {
                    finalUid= user.getUid();
                    finalemail = user.getEmail();
                    finalname = user.getDisplayName();
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };





    }

    private void onSignedInInitialize(String displayName) {


        if(mChildEventListener==null)
        {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {}
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void onSignedOutInitialize() {

        if(mChildEventListener!=null)
        {
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener=null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(mFirebaseAuthListener!=null)
        {
            mFirebaseAuth.removeAuthStateListener(mFirebaseAuthListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN)
        {
            if(resultCode== RESULT_OK)
            {
                Toast.makeText(BuySell.this,"signed in",Toast.LENGTH_LONG).show();
            }
            else if(resultCode== RESULT_CANCELED)
            {
                finish();
            }
        }
    }

}
