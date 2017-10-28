package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreA=0;
    int scoreB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void athree(View view)
    {
        scoreA+=3;
        displayA(scoreA);
    }

    public void atwo(View view)
    {
        scoreA+=2;
        displayA(scoreA);
    }

    public void afree(View view)
    {
        scoreA+=10;
        displayA(scoreA);
    }

    public void bthree(View view)
    {
        scoreB+=3;
        displayB(scoreB);
    }

    public void btwo(View view)
    {
        scoreB+=2;
        displayB(scoreB);
    }

    public void bfree(View view)
    {
        scoreB+=10;
        displayB(scoreB);
    }

    public void reset(View view){
        scoreA=0;
        scoreB=0;
        displayA(scoreA);
        displayB(scoreB);
    }


    private void displayA(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.total_score_A);
        quantityTextView.setText("" + number);
    }

    private void displayB(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.total_score_B);
        quantityTextView.setText("" + number);
    }
}
