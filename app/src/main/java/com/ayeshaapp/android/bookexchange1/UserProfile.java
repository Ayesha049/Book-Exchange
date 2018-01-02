package com.ayeshaapp.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by l on 11/4/17.
 */

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        Button btn = findViewById(R.id.Show_my_booklist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(UserProfile.this, ShowMyBookList.class);
                startActivity(familyIntent);
            }
        });







    }
}
