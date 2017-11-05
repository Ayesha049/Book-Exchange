package com.example.android.bookexchange1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by l on 11/5/17.
 */

public class ShowBookList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);



        final ArrayList<Book> books = new ArrayList<Book>();





        books.add(new Book("The Art Of Photography","Ayesha", "400tk", R.drawable.boi));
        books.add(new Book("Digital Photography", "Salsabil", "990tk", R.drawable.boi));
        books.add(new Book("Understanding Exposure", "Tasneem", "600tk", R.drawable.boi));

        books.add(new Book("The Art Of Photography","Ayesha", "400tk", R.drawable.boi));
        books.add(new Book("Digital Photography", "Salsabil", "990tk", R.drawable.boi));
        books.add(new Book("Understanding Exposure", "Tasneem", "600tk", R.drawable.boi));

        books.add(new Book("The Art Of Photography","Ayesha", "400tk", R.drawable.boi));
        books.add(new Book("Digital Photography", "Salsabil", "990tk", R.drawable.boi));
        books.add(new Book("Understanding Exposure", "Tasneem", "600tk", R.drawable.boi));




        BookAdapter adapter = new BookAdapter(this, books);



        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book Books = books.get(position);
                Advertise orderBook = new Advertise();
                //Log.v("showBookList","before orderBook");
                orderBook.passBook(Books);

                Intent familyIntent = new Intent(ShowBookList.this, orderBook.getClass());
                startActivity(familyIntent);
            }
        });






    }
}