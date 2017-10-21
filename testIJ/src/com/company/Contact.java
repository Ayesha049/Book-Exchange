package com.company;

public class Contact {
    String name;
    String email;
    String phoneNumber;

    void set(String name, String email, String phoneNumber)
    {
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    void print()
    {
        System.out.println(name +" " + email + " " + phoneNumber);
    }

}
