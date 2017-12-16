package com.example.android.bookexchange1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    static ArrayList<Book> books = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        books = new ArrayList<Book>();

        BookAdapter adapter = new BookAdapter(this, books);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = books.get(position);;

                Intent familyIntent = new Intent(ShowBookList.this, Advertise.class);
                //familyIntent.putExtra("Book",Books);
                startActivity(familyIntent);
            }
        });
    }


}