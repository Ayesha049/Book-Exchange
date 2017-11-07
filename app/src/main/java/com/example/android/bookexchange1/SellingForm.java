package com.example.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by l on 11/5/17.
 */

public class SellingForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_for_selling);


        Button continueBuying = findViewById(R.id.sell_book_add);
        continueBuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(SellingForm.this, ShowBookList.class);
                startActivity(familyIntent);
            }
        });


    }

}
