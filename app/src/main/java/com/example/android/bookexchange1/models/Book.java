package com.example.android.bookexchange1.models;

/**
 * Created by l on 11/5/17.
 */

public class Book {

    // TODO: consider implementing Parcelable here [re: https://developer.android.com/reference/android/os/Parcelable.html]

    private String bookName;
    private String bookWriter;
    private String price;
    private Integer imageId;

    public Book(String bookName, String bookWriter, String price, Integer imageId) {
        this.bookName = bookName;
        this.price = price;
        this.imageId = imageId;
        this.bookWriter = bookWriter;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPrice() {
        return price;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getBookWriter() {
        return bookWriter;
    }
}
