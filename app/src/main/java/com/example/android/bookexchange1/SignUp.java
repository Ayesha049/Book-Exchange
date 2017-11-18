package com.example.android.bookexchange1;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookexchange1.data.BookContract;

/**
 * Created by l on 11/3/17.
 */

public class SignUp extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmailId;
    private EditText mPhoneNo;
    private EditText mPassword;
    private EditText mConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button signnUp = findViewById(R.id.signn_up);

        mFirstName = findViewById(R.id.first_name_view);
        mLastName = findViewById(R.id.last_name_view);
        mEmailId = findViewById(R.id.email_id_view);
        mPhoneNo = findViewById(R.id.phone_number_view);
        mPassword = findViewById(R.id.password_view);
        mConfirmPassword = findViewById(R.id.confirm_password_view);


        signnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInsert = insertPerson();
                if (isInsert == true) {
                    Intent numbersIntent = new Intent(SignUp.this, Confirmation.class);
                    startActivity(numbersIntent);
                } else {
                    Toast.makeText(SignUp.this, "Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /**
     * Get user input from editor and save new pet into database.
     */
    private boolean insertPerson() {
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String emailId = mEmailId.getText().toString().trim();
        String phoneNo = mPhoneNo.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();


        if (password.equals(confirmPassword)) {
            ContentValues values = new ContentValues();
            values.put(BookContract.PersonEntry.COLUMN_PERSON_FIRSTNAME, firstName);
            values.put(BookContract.PersonEntry.COLUMN_PERSON_LASTNAME, lastName);
            values.put(BookContract.PersonEntry.COLUMN_PERSON_EMAIL, emailId);
            values.put(BookContract.PersonEntry.COLUMN_PERSON_PHONE, phoneNo);
            values.put(BookContract.PersonEntry.COLUMN_PERSON_PASSWORD, password);

            Uri newUri = getContentResolver().insert(BookContract.PersonEntry.PERSON_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Failed!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Successfully signed up!",
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return false;
    }


}
