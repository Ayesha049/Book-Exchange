package com.example.android.bookexchange1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TODO: add Facebook Stetho debugging tool [re: http://facebook.github.io/stetho/]
    // TODO: add vector drawable assets (e.g., XML / SVG format)
    // for all the icons identified in your mockup to drawable folder
    // TODO: implement vector drawables in UI according to wireframes
    // TODO: add accessibility features to your layouts by adding image content descriptions
    // (re: https://developer.android.com/guide/topics/ui/accessibility/apps.html)





    //// TODO: add the source of the BOI.jpeg image to README file
    //// TODO: add UI screenshots to the README file
    //// TODO: add hard-coded strings to string.xml;
    /// consider using xliff:g and / or Square Phrase for dynamic strings with localization
    /// [re: https://github.com/square/phrase]

    //// TODO: add hard-coded dimensions to dimens.xml
    //// TODO: consider maintaining an additional string.xml in Hindi language and / or any of your choice;
    /// a right to left language such as Arabic is good for learning mirroring UIs
    //// TODO: update layout files to reference strings from string.xml & dimensions from dimens.xml



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


    }
}
