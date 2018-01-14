package com.ayeshaapp.android.bookexchange1.models;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by a on 1/14/18.
 */
public class profile implements Parcelable {
    private String name;
    private String photourl;
    private String email;
    private String phoneno;

    public profile() { super(); }
    public profile(String name, String photourl, String email, String phoneno){
        this.name = name;
        this.photourl = photourl;
        this.email = email;
        this.phoneno = phoneno;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPhotourl(String photourl){
        this.photourl = photourl;
    }
    public String getPhotourl(){
        return photourl;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setPhoneno(String phoneno){
        this.phoneno = phoneno;
    }
    public String getPhoneno(){
        return phoneno;
    }

    protected profile(Parcel in) {
        this.name = in.readString();
        this.photourl = in.readString();
        this.email = in.readString();
        this.phoneno = in.readString();
    }
    public static final Creator<profile> CREATOR = new Creator<profile>() {
        @Override
        public profile createFromParcel(Parcel in) {
            return new profile(in);
        }
        @Override
        public profile[] newArray(int size) {
            return new profile[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.photourl);
        parcel.writeString(this.email);
        parcel.writeString(this.phoneno);
    }
}
