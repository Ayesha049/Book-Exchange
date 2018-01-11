package com.ayeshaapp.android.bookexchange1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayeshaapp.android.bookexchange1.models.Book;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by l on 1/12/18.
 */

public class MyBookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    public MyBookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }

        Book books = getItem(position);
        TextView tt = listItemView.findViewById(R.id.noti);

        if(books.getCount()==null || books.getCount()<=0)
        {

            tt.setVisibility(TextView.INVISIBLE);
        }
        else
        {
            tt.setText(books.getCount().toString());
            tt.setVisibility(TextView.VISIBLE);
        }


        TextView nameTextView = listItemView.findViewById(R.id.bookName);
        nameTextView.setText(books.getBookname());
        TextView priceTextView = listItemView.findViewById(R.id.bookPrice);
        priceTextView.setText(books.getPrice());
        ImageView bookphoto = listItemView.findViewById(R.id.image_item);

        Glide.with(bookphoto.getContext())
                .load(books.getPhotoUrl())
                .into(bookphoto);


        return listItemView;

    }
}
