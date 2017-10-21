package com.company;

public class Contact {
    private String name;
    private String email;
    private String phoneNumber;

    void set(String name, String email, String phoneNumber)
    {
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    String getName(){
        return this.name;
    }

    void print()
    {
        System.out.println(name +" " + email + " " + phoneNumber);
    }

}
