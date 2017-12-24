package com.ayeshaapp.android.bookexchange1.models;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by l on 11/5/17.
 */

public class Book implements Parcelable {

    private String bookname;
    private String authorname;
    private String price;
    private String booktype;
    private String photoUrl;

    public Book() {super();}

    public Book(String bookname,String authorname,String price,String booktype,String photoUrl) {
        this.bookname=bookname;
        this.authorname=authorname;
        this.price=price;
        this.booktype=booktype;
        this.photoUrl=photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static Creator<Book> getCreator() { return CREATOR;}

    public Book(Parcel parcel){
        this.bookname = parcel.readString();
        this.authorname = parcel.readString();
        this.price = parcel.readString();
        this.booktype = parcel.readString();
        this.photoUrl=parcel.readString();
    }


    public static final Parcelable.Creator<Book> CREATOR
            = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bookname);
        parcel.writeString(this.authorname);
        parcel.writeString(this.price);
        parcel.writeString(this.booktype);
        parcel.writeString(this.photoUrl);
    }
}
