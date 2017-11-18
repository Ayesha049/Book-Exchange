package com.example.android.bookexchange1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookexchange1.data.BookContract;

/**
 * Created by l on 11/3/17.
 */

public class LogIn extends AppCompatActivity {

    private EditText mEmailId;
    private EditText mPassword;
    public static String loginId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        Button logIn = findViewById(R.id.logggin);

        mEmailId = findViewById(R.id.email_id_view);
        mPassword = findViewById(R.id.password_view);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLogged = checkLogin();
                if(isLogged)
                {
                    Log.v("LogIn","logged in");
                    Intent numbersIntent = new Intent(LogIn.this, BuySell.class);
                    startActivity(numbersIntent);
                }
                else
                {
                    Toast.makeText(LogIn.this,"Login failed!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkLogin(){

        String emailId = mEmailId.getText().toString().trim();
        String Password = mPassword.getText().toString().trim();

        String[] Projection = {
                BookContract.PersonEntry.COLUMN_PERSON_EMAIL,
                BookContract.PersonEntry.COLUMN_PERSON_PASSWORD};

        String sel = BookContract.PersonEntry.COLUMN_PERSON_EMAIL + " = " + "'" + emailId + "'";
        Cursor cursor = getContentResolver().query(
                BookContract.PersonEntry.PERSON_URI,
                Projection,
                sel,
                null,
                null
        );

        if(cursor.getCount()!=1)
        {
            int count = cursor.getCount();
            String str = "";
            str+=count;
            Toast.makeText(LogIn.this, str ,Toast.LENGTH_LONG).show();
            return false;
        }

        int passwordIndex = cursor.getColumnIndex(BookContract.PersonEntry.COLUMN_PERSON_PASSWORD);
        //int personidindex = cursor.getColumnIndex(BookContract.PersonEntry.PERSON_ID);
        while (cursor.moveToNext()) {
            String readPassword = cursor.getString(passwordIndex);
            if (!Password.equals(readPassword)) {
                Toast.makeText(LogIn.this, "Password incorrect", Toast.LENGTH_LONG).show();
                return false;
            }
            else
            {
                loginId = emailId;
                String msg = "" + loginId;
                Toast.makeText(LogIn.this,msg,Toast.LENGTH_LONG).show();
            }
        }

        return true;
    }


}
