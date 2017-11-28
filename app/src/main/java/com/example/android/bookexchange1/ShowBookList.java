package com.example.android.bookexchange1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.bookexchange1.data.BookContract;
import com.example.android.bookexchange1.models.Book;

import java.util.ArrayList;

/**
 * Created by l on 11/5/17.
 */

public class ShowBookList extends AppCompatActivity {

    final static ArrayList<Book> books = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        addBooksToList();

       // books.add(new Book(R.drawable.boi,"name", "author", "200tk", "physics", "one@gmail.com"));

        //books.add(new Book("The Art Of Photography", "Ayesha", "400tk","novel", "ria"));
        BookAdapter adapter = new BookAdapter(this, books);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = books.get(position);;

                Intent familyIntent = new Intent(ShowBookList.this, Advertise.class);
                familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);
            }
        });
    }

    private void addBooksToList() {
        String[] Projection = {
                BookContract.AdvertisementEntry.COLUMN_AD_BOOK_NAME,
                BookContract.AdvertisementEntry.COLUMN_AD_AUTHOR_NAME,
                BookContract.AdvertisementEntry.COLUMN_AD_PRICE,
                BookContract.AdvertisementEntry.COLUMN_AD_BOOKTAG,
                BookContract.AdvertisementEntry.COLUMN_AD_PERSON_ID,
                //BookContract.AdvertisementEntry.COLUMN_AD_IMAGE

        };

        Cursor cursor = getContentResolver().query(
                BookContract.AdvertisementEntry.AD_URI,
                Projection,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int bookNameIndex = cursor.getColumnIndex(BookContract.AdvertisementEntry.COLUMN_AD_BOOK_NAME);
            int bookWriterIndex = cursor.getColumnIndex(BookContract.AdvertisementEntry.COLUMN_AD_AUTHOR_NAME);
            int priceIndex = cursor.getColumnIndex(BookContract.AdvertisementEntry.COLUMN_AD_PRICE);
            int bookTagIndex = cursor.getColumnIndex(BookContract.AdvertisementEntry.COLUMN_AD_BOOKTAG);
            int personIdIndex = cursor.getColumnIndex(BookContract.AdvertisementEntry.COLUMN_AD_PERSON_ID);
            //int imageIndex = cursor.getColumnIndex(BookContract.AdvertisementEntry.COLUMN_AD_IMAGE);


            String bookName = cursor.getString(bookNameIndex);
            String bookWriter = cursor.getString(bookWriterIndex);
            String price = cursor.getString(priceIndex);
            String bookTag = cursor.getString(bookTagIndex);
            String personId = cursor.getString(personIdIndex);
            //byte[] image = cursor.getBlob(imageIndex);

            books.add(new Book(bookName, bookWriter, price, bookTag, personId));
            cursor.moveToNext();
        }
    }
}