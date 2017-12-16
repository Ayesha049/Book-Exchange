package com.example.android.bookexchange1.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by l on 11/5/17.
 */

public class Book  {

    private String bookname;
    private String authorname;
    private String price;
    private String booktype;

    public Book() {}

    public Book(String bookname,String authorname,String price,String booktype) {
        this.bookname=bookname;
        this.authorname=authorname;
        this.price=price;
        this.booktype=booktype;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {

        return price;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthorname() {

        return authorname;

    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookname() {

        return bookname;
    }
}
