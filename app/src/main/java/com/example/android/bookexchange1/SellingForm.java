package com.example.android.bookexchange1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.bookexchange1.data.BookContract;
import com.example.android.bookexchange1.data.BookDbHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by l on 11/5/17.
 */

public class SellingForm extends AppCompatActivity {

    private EditText mbookName;
    private EditText mbookAuthor;
    private ImageView mbookimage;
    private EditText mbookprice;
    private EditText mbookTag;
    private String mpersonId;

    final int REQUEST_CODE_GALLERY = 999;

    //private BookDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_for_selling);

        //mDbHelper = new BookDbHelper(this);

        Button continueBuying = findViewById(R.id.form_for_selling_add_button);
        mbookName = findViewById(R.id.form_for_selling_book_name);
        mbookAuthor = findViewById(R.id.form_for_selling_author);
        mbookimage = findViewById(R.id.form_for_selling_add_image);
        mbookprice = findViewById(R.id.form_for_selling_price);
        mbookTag = findViewById(R.id.form_for_selling_book_type);

        mbookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SellingForm.this,"imageview clicked",Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(SellingForm.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);

            }
        });

        continueBuying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertBook();
                Intent familyIntent = new Intent(SellingForm.this, ShowBookList.class);
                startActivity(familyIntent);

            }
        });


    }

    public byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mbookimage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




    private void insertBook() {

        //SQLiteDatabase database = mDbHelper.getWritableDatabase();

        byte[] bookImageByte = imageViewToByte(mbookimage);

        String bookName = mbookName.getText().toString().trim();
        String AuthorName = mbookAuthor.getText().toString().trim();
        String pr = mbookprice.getText().toString().trim();
        int price = Integer.parseInt(pr);
        String booktag = mbookTag.getText().toString().trim();
        mpersonId = LogIn.loginId;

        ContentValues values = new ContentValues();
        values.put(BookContract.AdvertisementEntry.COLUMN_AD_BOOK_NAME, bookName);
        values.put(BookContract.AdvertisementEntry.COLUMN_AD_AUTHOR_NAME, AuthorName);
        values.put(BookContract.AdvertisementEntry.COLUMN_AD_IMAGE, bookImageByte);
        values.put(BookContract.AdvertisementEntry.COLUMN_AD_PRICE, price);
        values.put(BookContract.AdvertisementEntry.COLUMN_AD_BOOKTAG, booktag);
        values.put(BookContract.AdvertisementEntry.COLUMN_AD_PERSON_ID, mpersonId);

        //long ins = database.insert(BookContract.AdvertisementEntry.AD_TABLE_NAME, null, values);
        //String str = Long.toString(ins);
        //Toast.makeText(SellingForm.this,str,Toast.LENGTH_LONG).show();


        Uri newUri = getContentResolver().insert(BookContract.AdvertisementEntry.AD_URI, values);
        if (newUri == null) {
            Toast.makeText(this, "Failed!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Successfully added to booklist",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
