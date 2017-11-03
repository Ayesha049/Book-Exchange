package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public int numberOfCoffee=0;
    boolean chk=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        numberOfCoffee=numberOfCoffee+1;
        display(numberOfCoffee);
    }

    public void decrement(View view){
        numberOfCoffee=numberOfCoffee-1;
        display(numberOfCoffee);
    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox ckk = (CheckBox) findViewById(R.id.chckbox);
        chk=ckk.isChecked();
        Log.v("MainActivity","Checked"+chk);


        EditText edittext = (EditText) findViewById(R.id.InputName);
        String name= edittext.getText().toString();

        String priceMessage="";
        priceMessage+="name : " + name + '\n';
        priceMessage+="add whipped cream? " + chk + "\n";
        priceMessage+="quantity : " + numberOfCoffee +"\n";
        priceMessage+="Total : " + "$"+numberOfCoffee*10 + "\n" + "Thank You!";
        displayMessage(priceMessage);


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}