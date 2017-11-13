package com.example.android.bookexchange1.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by l on 11/5/17.
 */

public class Book implements Parcelable {

    // TODO: consider implementing Parcelable here [re: https://developer.android.com/reference/android/os/Parcelable.html]

    private String bookName;
    private String bookWriter;
    private String price;
    private Integer imageId;

    public Book() { super(); }

    public Book(String bookName, String bookWriter, String price, Integer imageId) {
        this.bookName = bookName;
        this.price = price;
        this.imageId = imageId;
        this.bookWriter = bookWriter;
    }

    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getBookName() {
        return bookName;
    }

    public void setPrice(String price) { this.price = price; }
    public String getPrice() {
        return price;
    }

    public void setImageId(Integer imageId) { this.imageId = imageId; }
    public Integer getImageId() {
        return imageId;
    }

    public void setBookWriter(String bookWriter) { this.bookWriter = bookWriter; }
    public String getBookWriter() {
        return bookWriter;
    }


    public static Creator<Book> getCreator() { return CREATOR;}
    public Book(Parcel parcel){
        this.bookName = parcel.readString();
        this.bookWriter = parcel.readString();
        this.price = parcel.readString();
        this.imageId = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bookName);
        parcel.writeString(this.bookWriter);
        parcel.writeString(this.price);
        parcel.writeInt(this.imageId);
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


}
