package com.example.android.bookexchange1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookexchange1.data.BookContract;
import com.example.android.bookexchange1.data.BookContract.PersonEntry;
import com.example.android.bookexchange1.data.BookDbHelper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener{

    private SignInButton gsignin;
    private GoogleApiClient gApiClient;
    private static final Integer Req_code = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Button signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(numbersIntent);
            }
        });


        Button login = findViewById(R.id.log_in);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, LogIn.class);
                startActivity(numbersIntent);
            }
        });


        GoogleSignInOptions signinoption = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gApiClient = new GoogleApiClient.Builder(this).
                        enableAutoManage(this,this).
                        addApi(Auth.GOOGLE_SIGN_IN_API,signinoption).build();


        gsignin = findViewById(R.id.google_signin);
        gsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsigninMethod();
                //Toast.makeText(MainActivity.this,"sign in successful",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void gsigninMethod()
    {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(gApiClient);
        startActivityForResult(intent,Req_code);
        //Toast.makeText(MainActivity.this,"sign in successful",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(MainActivity.this,"sign in successful",Toast.LENGTH_LONG).show();
        if(resultCode== Req_code)
        {
            //Toast.makeText(MainActivity.this,"sign in successful",Toast.LENGTH_LONG).show();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                Toast.makeText(MainActivity.this,"sign in successful",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"sign in failed",Toast.LENGTH_LONG).show();
            }
        }
    }
}
