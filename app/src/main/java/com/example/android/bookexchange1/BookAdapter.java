package com.example.android.bookexchange1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bookexchange1.models.Book;

import java.util.ArrayList;

/**
 * Created by l on 11/5/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    public BookAdapter(Activity context, ArrayList<Book> books) {
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

        ImageView bookImageView = listItemView.findViewById(R.id.bookPic);
        byte[] bookImage = books.getImage();

        final Bitmap bmp = BitmapFactory.decodeByteArray(bookImage,0,bookImage.length);
        bookImageView.setImageBitmap(bmp);

        //bookImageView.setImageResource(books.getImageId());

        TextView nameTextView = listItemView.findViewById(R.id.bookName);
        nameTextView.setText(books.getBookName());
        TextView priceTextView = listItemView.findViewById(R.id.bookPrice);
        priceTextView.setText(books.getPrice());
        return listItemView;

    }
}
