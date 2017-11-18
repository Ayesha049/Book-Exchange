package com.example.android.bookexchange1.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by l on 11/5/17.
 */

public class Book implements Parcelable {


    private String bookName;
    private String bookWriter;
    private String price;
    private Integer imageId;
    private String bookTag;
    private String personId;
    private byte[] image;

    public Book() { super(); }

    public Book(Integer imageId, String bookName, String bookWriter, String price, String tag,String idd, byte[] image) {
        this.bookName = bookName;
        this.price = price;
        this.imageId = imageId;
        this.bookWriter = bookWriter;
        this.bookTag=tag;
        this.personId=idd;
        this.image = image;
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

    public void setBooktag(String bookWriter) { this.bookTag= bookWriter; }
    public String getBookTag() {
        return bookTag;
    }

    public void setPersonId(String personId) { this.personId = personId; }
    public String getPersonId() {
        return personId;
    }

    public void setImage(byte[] image) { this.image = image; }
    public byte[] getImage() { return image; }




    public static Creator<Book> getCreator() { return CREATOR;}
    public Book(Parcel parcel){
        this.imageId = parcel.readInt();
        this.bookName = parcel.readString();
        this.bookWriter = parcel.readString();
        this.price = parcel.readString();
        this.imageId = parcel.readInt();
        this.bookTag = parcel.readString();
        this.personId = parcel.readString();
        this.image = new byte[parcel.readInt()];
        parcel.readByteArray(image);
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
        parcel.writeString(this.bookTag);
        parcel.writeString(this.personId);
        parcel.writeByteArray(this.image);
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
